package edu.ucdenver.ccp.datasource.fileparsers.test;

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

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import edu.ucdenver.ccp.common.file.FileUtil;
import edu.ucdenver.ccp.common.io.ClassPathUtil;
import edu.ucdenver.ccp.common.test.DefaultTestCase;
import edu.ucdenver.ccp.datasource.fileparsers.RecordReader;

public abstract class RecordReaderTester extends DefaultTestCase {

	protected File sampleInputFile;
	protected File outputDirectory;

	@Before
	public void setUp() throws Exception {
		if (getSampleFileName() != null) {
			sampleInputFile = folder.newFile(getSampleFileName());
			ClassPathUtil.copyClasspathResourceToFile(getClass(), getSampleFileName(), sampleInputFile);
		}

		outputDirectory = FileUtil.appendPathElementsToDirectory(folder.newFolder("flat-files"), "autogenerated",
				"20100101");
		FileUtil.mkdir(outputDirectory);
	}

	protected abstract String getSampleFileName();

	@Test
	public void testIteratorPattern() throws Exception {
		RecordReader<?> recordReader = initSampleRecordReader();
		int expectedRecordCount = 100;
		for (int i = 0; i < expectedRecordCount; i++)
			assertTrue("Repeated calls to parser.hasNext() should not advance the iterator.", recordReader.hasNext());
	}

	protected abstract RecordReader<?> initSampleRecordReader() throws IOException;

}