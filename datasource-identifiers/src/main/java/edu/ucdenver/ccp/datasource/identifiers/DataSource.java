package edu.ucdenver.ccp.datasource.identifiers;

/*
 * #%L
 * Colorado Computational Pharmacology's common module
 * %%
 * Copyright (C) 2012 - 2014 Regents of the University of Colorado
 * %%
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the Regents of the University of Colorado nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

import java.util.EnumSet;

public enum DataSource implements IDataSource {
	/**
	 * Used to indicate "any" data source
	 */
	ANY,
	/**
	 * http://www.nal.usda.gov/
	 */
	AFFYMETRIX,
	AGRICOLA,
	AHFS,
	AFCS,
	ALFRED,
	ALLERGOME,
	ANIMALQTLDB,
	APHIDBASE,
	APIDBCRYPTODB,
	ARACHNOSERVER,
	ARRAYEXPRESS,
	ASRP,
	BEEBASE,
	BEETLEBASE,
	BGEE,
	BIND,
	BIND_TRANSLATION,
	BINDING_DB,
	BIOCYC,
	BIOGRID,
	BRENDA,
	KABOB,
	CAMJEDB,
	CCDS,
	CCP,
	CGNC,
	CHEMSPIDER,
	CTD,
	CORUM,
	COSMIC,
	CYGD,
	DAILYMED,
	DBJ,
	DDBJ, // DNA Data Bank of Japan
	DBSNP,
	DICTYBASE,
	DIP,
	DOI,
	/** drugs product database */
	DPD,
	DRUGBANK,
	/** drug code directory */
	DRUGCODEDIRECTORY,
	DRUG_PRODUCTS_DB,
	ECOCYC,
	ECOGENE,
	/** Entrez Gene */
	EG,
	ELSEVIER,
	EMB,
	EMBL,
	ENSEMBL,
	ENZYME_COMMISSION,
	FLYBASE,
	GAD,
	/**
	 * GenBank Nucleic Acid Sequence Database
	 */
	GENBANK,
	GENE3D,
	GENATLAS,
	GENECARD,
	GENSAT,
	GO,
	GOA,
	GOA_REFERENCE,
	GO_EVIDENCE,
	GO_REFERENCE,
	GOPAD,
	GUIDE_TO_PHARMACOLOGY,
	HAMAP,
	HCDM,
	HGNC,
	HOMOLOGENE,
	HOMEODB,
	HORDE,
	HPRD,
	HUGE,
	HUMANCYC,
	IAO,
	IMGT,
	/**
	 * International Molecular Exchange Consortium (http://www.imexconsortium.org/)
	 */
	IMEX,
	INCRNADB,
	INNATEDB,
	/**
	 * International Nucleotide Sequence Database Collaboration
	 */
	INSDC,
	INTACT,
	INTERFIL,
	INTERPRO,
	IPI,
	IREFWEB,
	IUPHAR,
	KEGG,
	MAMIT_TRNA_DB,
	MP,
	MAIZEGDB,
	MATRIXDB,
	MEDGEN,
	MESH,
	MEROPS,
	MGI,
	MGI_REFERENCE,
	MI_ONTOLOGY,
	MINT,
	MIRBASE,
	MIRTE,
	MODBASE,
	MPACT,
	MPIDB,
	MUTDB,
	NASONIABASE,
	NATIONAL_DRUG_CODE_DIRECTORY,
	NBO,
	NCBI_TAXON,
	NCBI_TRACE,
	OBO,
	OPHID,
	ORPHANET,
	OMIM,
	OWL,
	PANTHER,
	PATHEMA,
	PBR,
	PDB,
	PDB_LIGAND,
	PHARMGKB,
	PFAM,
	PII,
	PICTAR,
	PIR,
	PIRNABANK,
	PIRSF,
	PM,
	PMC,
	PREMOD,
	PRF,
	PRINTS,
	PRODOM,
	PROSITE,
	PSEUDOCAP,
	PSEUDOGENE_ORG,
	PW,
	RATMAP,
	RDO,
	REACTOME,
	REFSEQ,
	REFSNP,
	RFAM,
	RGD,
	UNIPROT_PREDICTION,
	SGD,
	SMART,
	SNOMEDCT,
	SNORNABASE,
	SO,
	SUPERFAM,
	TAIR,
	TIGRFAMS,
	TRANSFAC,
	UBERON,
	UCSCGENOMEBROWSER,
	UMLS,
	UNCHAR_PFAM,
	UNIGENE,
	UNIPARC,
	UNIPROT,
	UNIREF,
	URL,
	VBRC,
	VECTORBASE,
	VEGA,
	WHOCC,
	WIKIPEDIA,
	WORMBASE,
	XENBASE,
	ZFIN,
	ZNF_GENE_CATALOG,
	/** relation ontology */
	RO,
	/** Protein Ontology - {@link http://pir.georgetown.edu/pro/} */
	PR,
	CHEBI,
	CL,
	MOD,
	/**
	 * Johns Hopkins University Genome Data Bank
	 */
	GDB,
	CLINICAL_TRIALS_GOV,
	/**
	 * International Standard Randomised Controlled Trial Number
	 */
	ISRCTN,
	/**
	 * NLM's Gene Expression Omnibus
	 */
	GEO,
	PUBCHEM_SUBSTANCE,
	PUBCHEM_COMPOUND,
	PUBCHEM_BIOASSAY,
	CHEMICAL_ABSTRACTS_SERVICE,
	THERAPEUTIC_TARGETS_DB,

	CAZY,
	CGD,
	CHEMBL,
	CHITARS,
	CLEANEX,
	COMPLUYEAST_2DPAGE,
	CONOSERVER,
	DISPROT,
	DMDM,
	DNASU,
	DOSAC_COBS_2DPAGE,
	ECHOBASE,
	EGGNOG,
	ENSEMBLBACTERIA,
	ENSEMBLFUNGI,
	ENSEMBLMETAZOA,
	ENSEMBLPLANTS,
	ENSEMBLPROTISTS,
	ENZYME,
	EUHCVDB,
	EUPATHDB,
	EVOLUTIONARYTRACE,
	GENEFARM,
	GENETREE,
	GENEVESTIGATOR,
	GENOLIST,
	GENOMEREVIEWS,
	GENOMERNAI,
	GERMONLINE,
	GLYCOSUITEDB,
	GPCRDB,
	GRAMENE,
	H_INVDB,
	HOGENOM,
	HOVERGEN,
	HPA,
	HSSP,
	INPARANOID,
	LEGIOLIST,
	LEPROMA,
	MICADO,
	MYCOCLAP,
	NEXTBIO,
	NEXTPROT,
	OGP,
	OMA,
	ORTHODB,
	PATHWAY_INTERACTION_DB,
	PATRIC,
	PAXDB,
	PDB_J,
	PDB_EUROPE,
	PDB_SUM,
	PEPTIDEATLAS,
	PEROXIBASE,
	PHOSPHOSITE,
	PHOSSITE,
	PHYLOMEDB,
	PMAP_CUTDB,
	POMBASE,
	PPTASEDB,
	PRIDE,
	PROMEX,
	PROTCLUSTDB,
	PROTEINMODELPORTAL,
	PROTONET,
	REBASE,
	REPRODUCTION_2DPAGE,
	ROUGE,
	SABIO_RK,
	SBKB,
	SMR,
	SOURCE,
	STRING,
	SUPFAM,
	SWISS_2DPAGE,
	TCDB,
	TUBERCULIST,
	UCD_2DPAGE,
	UNIPATHWAY,
	WORLD_2DPAGE, MEDLINE, BIOPARADIGMS;

	public static EnumSet<DataSource> GENE_OR_GENE_PRODUCT_SOURCES = EnumSet.of(EG, UNIPROT, MGI, HGNC, HPRD, REFSEQ,
			DIP, IREFWEB, EMBL, PR, PHARMGKB);

	public static boolean isDataSource(String input) {
		try {
			DataSource.valueOf(input);
			return true;
		} catch (NullPointerException e) {
			return false;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}
