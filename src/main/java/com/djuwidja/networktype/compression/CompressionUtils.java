/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype.compression;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
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
        } catch (Exception e){
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
        } catch (Exception e){
            throw new CompressionUtilsException(e.getMessage());
        }
    }
}
