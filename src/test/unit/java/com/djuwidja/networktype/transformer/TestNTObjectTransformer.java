package com.djuwidja.networktype.transformer;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.djuwidja.networktype.NTDict;
import com.djuwidja.networktype.transformer.NTObjectTransformerException;
import com.djuwidja.networktype.transformer.NTObjectTransformer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.junit.Assert;

@ExtendWith(SpringExtension.class)
public class TestNTObjectTransformer {
	private NTObjectTransformer transformerService = new NTObjectTransformer();
    
    @Test
    public void testSuccess() throws NTObjectTransformerException {
    	TestClass testObj = TestClass.buildSuccessTestObj();
    	
    	NTDict dict = transformerService.transformObj(testObj);
    	String dictJsonStr = dict.toJsonString();
    	Assert.assertEquals(testObj.toNTDict().toJsonString(), dictJsonStr);
    }
    
    @Test
    public void testEmpty() throws NTObjectTransformerException {
    	TestClass testObj = TestClass.buildEmptyTestObj();
    	
    	NTDict dict = transformerService.transformObj(testObj);
    	String dictJsonStr = dict.toJsonString();
    	Assert.assertEquals(testObj.toNTDict().toJsonString(), dictJsonStr);
    }
    
    @Test
    public void testWithNullField() throws NTObjectTransformerException {
    	TestClass testObj = TestClass.buildTestObjWithNulls();
    	
    	NTDict dict = transformerService.transformObj(testObj);
    	String dictJsonStr = dict.toJsonString();
    	Assert.assertEquals(testObj.toNTDict().toJsonString(), dictJsonStr);
    }
    
    @Test
    public void testNestedObj() throws NTObjectTransformerException {
    	NestedTestClass testObj = NestedTestClass.buildSuccessTestObj();
    	NTDict dict = transformerService.transformObj(testObj);
    	String dictJsonStr = dict.toJsonString();
    	Assert.assertEquals(testObj.toNTDict().toJsonString(), dictJsonStr);
    }
    
    @Test
    public void testNestedEmpty() throws NTObjectTransformerException {
    	NestedTestClass testObj = NestedTestClass.buildEmptyTestObj();
    	NTDict dict = transformerService.transformObj(testObj);
    	String dictJsonStr = dict.toJsonString();
    	Assert.assertEquals(testObj.toNTDict().toJsonString(), dictJsonStr);
    }
    
    @Test
    public void testNestedWithNullField() throws NTObjectTransformerException {
    	NestedTestClass testObj = NestedTestClass.buildTestObjWithNulls();
    	NTDict dict = transformerService.transformObj(testObj);
    	String dictJsonStr = dict.toJsonString();
    	Assert.assertEquals(testObj.toNTDict().toJsonString(), dictJsonStr);
    }
    
    @Test
    public void testJsonObj() throws NTObjectTransformerException, IOException {
    	byte[] jsonBytes = Files.readAllBytes(Paths.get("./src/test/unit/resources/TestJson.json"));
    	String content = new String(jsonBytes);
    	
    	JSONObject jsonObj = new JSONObject(content);
    	NTDict dict = transformerService.transformJson(jsonObj);
    	String strResult = dict.toJsonString().replace(" ", "");
    	String expected = jsonObj.toString();
    	
    	Assert.assertEquals(expected, strResult);
    	
    }
}
