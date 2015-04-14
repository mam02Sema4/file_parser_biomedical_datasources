/*
 * Copyright (C) 2009 Center for Computational Pharmacology, University of Colorado School of Medicine
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 */
package edu.ucdenver.ccp.fileparsers.transfac;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import edu.ucdenver.ccp.common.file.CharacterEncoding;
import edu.ucdenver.ccp.datasource.fileparsers.MultiLineFileRecordReader;
import edu.ucdenver.ccp.datasource.identifiers.ncbi.gene.EntrezGeneID;
import edu.ucdenver.ccp.datasource.identifiers.transfac.TransfacFactorID;
import edu.ucdenver.ccp.datasource.identifiers.transfac.TransfacGeneID;

/**
 * This class is used to parse the Transfac gene.dat file, available only via registration (and
 * potential payment) with www.gene-regulation.com
 * 
 * @author Bill Baumgartner
 * 
 */
public class TransfacGeneDatFileParser extends MultiLineFileRecordReader<TransfacGeneDatFileData> {

	public static final String GENE_DAT_FILE_NAME = "gene.dat";
	
	private static Logger logger = Logger.getLogger(TransfacGeneDatFileParser.class);

	public TransfacGeneDatFileParser(File file, CharacterEncoding encoding) throws IOException {
		super(file, encoding, null);
	}

	/**
	 * During the initialization we want to skip the header, determine the character offsets for the
	 * column and load the first record.
	 */
	protected void initialize() throws IOException {
		/* Read until line starts with AC */
		line = readLine();
		while (!line.getText().startsWith("AC"))
			line = readLine();
		super.initialize();
	}

	@Override
	protected MultiLineBuffer compileMultiLineBuffer() throws IOException {
		if (line == null)
			return null;
		MultiLineBuffer transfacDataRecordBuffer = new MultiLineBuffer();
		do {
			transfacDataRecordBuffer.add(line);
			line = readLine();
		} while (line != null && !line.getText().startsWith("//"));
		line = readLine();
		return transfacDataRecordBuffer;
	}

	@Override
	protected TransfacGeneDatFileData parseRecordFromMultipleLines(MultiLineBuffer transfacDataRecordBuffer) {
		return TransfacGeneDatFileData.parseTransfacGeneDataRecord(transfacDataRecordBuffer);
	}

	/**
	 * Returns a mapping from Transfac internal gene ID (G00001) to Entrez Gene ID (if there is one
	 * associated in the gene.dat file)
	 * 
	 * @param transfacGeneDatFile
	 * @return
	 */
	public static Map<TransfacGeneID, EntrezGeneID> getTransfacGeneID2EntrezGeneIDMap(File transfacGeneDatFile,
			CharacterEncoding encoding) {
		Map<TransfacGeneID, EntrezGeneID> transfacGeneID2EntrezGeneIDMap = new HashMap<TransfacGeneID, EntrezGeneID>();

		TransfacGeneDatFileParser parser = null;
		try {
			parser = new TransfacGeneDatFileParser(transfacGeneDatFile, encoding);
			while (parser.hasNext()) {
				TransfacGeneDatFileData dataRecord = parser.next();
				TransfacGeneID transfacInternalGeneID = dataRecord.getTransfacGeneID();
				EntrezGeneID entrezGeneID = dataRecord.getEntrezGeneDatabaseReferenceID();
				if (entrezGeneID != null) {
					if (!transfacGeneID2EntrezGeneIDMap.containsKey(transfacInternalGeneID)) {
						transfacGeneID2EntrezGeneIDMap.put(transfacInternalGeneID, entrezGeneID);
					} else {
						logger.warn("Duplicate transfac gene ID encountered: " + transfacInternalGeneID);
					}
				}
			}
			parser.close();
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}

		return transfacGeneID2EntrezGeneIDMap;
	}

	/**
	 * Returns a mapping from Transfac factor ID (T00001) to the encoding gene ID (G00001)
	 * 
	 * @param transfacGeneDatFile
	 * @return
	 */
	public static Map<TransfacFactorID, TransfacGeneID> getTransfacFactorID2EncodingGeneIDMap(File transfacGeneDatFile,
			CharacterEncoding encoding) {
		Map<TransfacFactorID, TransfacGeneID> transfacFactorID2EncodingGeneIDMap = new HashMap<TransfacFactorID, TransfacGeneID>();

		TransfacGeneDatFileParser parser = null;
		try {
			parser = new TransfacGeneDatFileParser(transfacGeneDatFile, encoding);
			while (parser.hasNext()) {
				TransfacGeneDatFileData dataRecord = parser.next();
				TransfacGeneID transfacInternalGeneID = dataRecord.getTransfacGeneID();
				Set<TransfacFactorID> encodedFactorIDs = dataRecord.getEncodedFactorIDs();

				for (TransfacFactorID encodedFactorID : encodedFactorIDs) {
					if (!transfacFactorID2EncodingGeneIDMap.containsKey(encodedFactorID)) {
						transfacFactorID2EncodingGeneIDMap.put(encodedFactorID, transfacInternalGeneID);
					} else {
						logger.warn("Duplicate encoded factor ID encountered: " + encodedFactorID);
					}
				}
			}
			parser.close();
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}

		return transfacFactorID2EncodingGeneIDMap;
	}

	// public static void main(String[] args) {
	// /* how many records have an encoded factor, but no entrez gene ID?? */
	// try {
	// TransfacGeneDatFileParser parser = new TransfacGeneDatFileParser(
	// "/Volumes/hal-1/hanalyzer/research/accelerator/data/expert-data/mouse-data/expert-data/explicit-experts/Transfac/raw/gene.dat");
	//
	// int recordCount = 0;
	// int recordWithEncodedFactor = 0;
	// int recordWithEntrezGeneID = 0;
	// int recordWithBothEncodedFactorAndEntrezGeneID = 0;
	// int recordWithMgiID = 0;
	// int recordWithBothEncodedFactorAndMgiID = 0;
	//
	// while (parser.hasNext()) {
	// TransfacGeneDatFileData dataRecord = parser.next();
	// recordCount++;
	// if (dataRecord.getEncodedFactorIDs() != null && dataRecord.getEncodedFactorIDs().size() > 0)
	// {
	// recordWithEncodedFactor++;
	// }
	// if (dataRecord.getEntrezGeneDatabaseReferenceID() != null) {
	// recordWithEntrezGeneID++;
	// }
	// if ((dataRecord.getEncodedFactorIDs() != null && dataRecord.getEncodedFactorIDs().size() > 0)
	// & (dataRecord.getEntrezGeneDatabaseReferenceID() != null)) {
	// recordWithBothEncodedFactorAndEntrezGeneID++;
	// }
	// if (dataRecord.getMgiDatabaseReferenceID() != null) {
	// recordWithMgiID++;
	// }
	// if ((dataRecord.getEncodedFactorIDs() != null && dataRecord.getEncodedFactorIDs().size() > 0)
	// & (dataRecord.getMgiDatabaseReferenceID() != null)) {
	// recordWithBothEncodedFactorAndMgiID++;
	// }
	// }
	//
	// System.out.println("Record Count: " + recordCount);
	// System.out.println("Records with Encoded Factor(s): " + recordWithEncodedFactor);
	// System.out.println("Records with Entrez Gene ID: " + recordWithEntrezGeneID);
	// System.out.println("Records with BOTH encoded factor(s) and entrez gene id: "
	// + recordWithBothEncodedFactorAndEntrezGeneID);
	// System.out.println("Records with MGI ID: " + recordWithMgiID);
	// System.out
	// .println("Records with BOTH encoded factor(s) and MGI ID: " +
	// recordWithBothEncodedFactorAndMgiID);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

}
