/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype.compression;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;
import org.springframework.stereotype.Component;
/**
 * This helper class compresses and decompresses byte arrays using zip.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
@Component
public class CompressionUtils {
    /**
     * Decompress the byte array with unzip.
     * @param data byte array to be unzip.
     * @return byte[].
     * @throws CompressionUtilsException fails when unable to unzip byte array.
     */
    public byte[] decompress(byte[] data) throws CompressionUtilsException {
        try {
            Inflater inflater = new Inflater();
            inflater.setInput(data);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
            byte[] buffer = new byte[1024];
            while (!inflater.finished()){
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
            
            byte[] result = outputStream.toByteArray();
            return result;
        } catch (final Exception e){
            throw new CompressionUtilsException(e.getMessage());
        }
    }
    /**
     * Compress the byte array with zip.
     * @param data byte array to be zip.
     * @return byte[].
     * @throws CompressionUtilsException fails when unable to zip byte array.
     */
    public byte[] compress(byte[] data) throws CompressionUtilsException {
        try {        	
            Deflater deflater = new Deflater();
            deflater.setInput(data);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
            deflater.finish();

            byte[] buffer = new byte[1024];
            while (!deflater.finished()){
                int count = deflater.deflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();

            byte[] output = outputStream.toByteArray();
            return output;
        } catch (final Exception e) {
            throw new CompressionUtilsException(e.getMessage());
        }
    }
    /**
     * Decompress the byte array with gzip.
     * @param data byte array to be decompressed by gzip.
     * @return byte[].
     * @throws CompressionUtilsException fails when unable to decompress byte array with gzip.
     */
    public byte[] decompressGZip(byte[] data) throws CompressionUtilsException {
    	try {
    		ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
    		GZIPInputStream gzipStream = new GZIPInputStream(inputStream);
    		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    		
    		byte[] buffer = new byte[1024];
    		int len;
    		while ((len = gzipStream.read(buffer)) != -1) {
    			outputStream.write(buffer, 0, len);
    		}
    		outputStream.close();
    		gzipStream.close();
    		inputStream.close();
    		
    		byte[] output = outputStream.toByteArray();
    		return output;    		
    	} catch (final Exception e) {
    		throw new CompressionUtilsException(e.getMessage());
    	}
    }
    /**
     * Compress the byte array with gzip.
     * @param data byte array to be gzip.
     * @return byte[].
     * @throws CompressionUtilsException fails when unable to gzip byte array.
     */
    public byte[] compressGZip(byte[] data) throws CompressionUtilsException {
    	try {
    		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
    		GZIPOutputStream gzipStream = new GZIPOutputStream(outputStream);
    		for (int i = 0; i < data.length; i++) {
    			gzipStream.write(data[i]);
    		}
    		gzipStream.close();
    		outputStream.close();
    		
    		byte[] output = outputStream.toByteArray();
    		return output;    		
    	} catch (final Exception e) {
    		throw new CompressionUtilsException(e.getMessage());
    	}
    }
}
