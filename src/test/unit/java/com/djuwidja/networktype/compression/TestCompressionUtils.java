package com.djuwidja.networktype.compression;

import java.nio.charset.StandardCharsets;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.Assert;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class TestCompressionUtils {
	private CompressionUtils compressionUtils = new CompressionUtils();
	
	@Test
	public void testCompression() throws CompressionUtilsException {
		String originalText = "Ich heiße Otto von Bismarch. Ich komme aus Berlin. Das ist in Nordwesten von Deutschland. Freut mich!";
		byte[] compressedTextBytes = compressionUtils.compress(originalText.getBytes());
		byte[] textBytes = compressionUtils.decompress(compressedTextBytes);
		String processedText = new String(textBytes, StandardCharsets.UTF_8);
		
		Assert.assertEquals(originalText, processedText);
	}
	
	@Test
	public void testCompressionEmpty() throws CompressionUtilsException {
		byte[] originalBytes = new byte[0];
		byte[] compressedTextBytes = compressionUtils.compress(originalBytes);
		byte[] processedBytes = compressionUtils.decompress(compressedTextBytes);
		
		Assert.assertEquals(originalBytes.length, processedBytes.length);
	}
	
	@Test
	public void testCompressionNull() {
		try {
			compressionUtils.compress(null);
			Assert.fail("Exception is not thrown");
		} 
		catch (final CompressionUtilsException e) {
		
		}
	}
	
	@Test
	public void testCompressionGZip() throws CompressionUtilsException {
		String originalText = "Ich heiße Otto von Bismarch. Ich komme aus Berlin. Das ist in Nordwesten von Deutschland. Freut mich!";
		byte[] compressedTextBytes = compressionUtils.compressGZip(originalText.getBytes());
		byte[] textBytes = compressionUtils.decompressGZip(compressedTextBytes);
		String processedText = new String(textBytes, StandardCharsets.UTF_8);
		
		Assert.assertEquals(originalText, processedText);
	}
	
	@Test
	public void testCompressionGZipEmpty() throws CompressionUtilsException {
		byte[] originalBytes = new byte[0];
		byte[] compressedTextBytes = compressionUtils.compressGZip(originalBytes);
		byte[] processedBytes = compressionUtils.decompressGZip(compressedTextBytes);
		
		Assert.assertEquals(originalBytes.length, processedBytes.length);
	}
	
	@Test
	public void testCompressionGZipNull() {
		try {
			compressionUtils.compressGZip(null);
			Assert.fail("Exception is not thrown");
		} 
		catch (final CompressionUtilsException e) {
		
		}
	}
}
