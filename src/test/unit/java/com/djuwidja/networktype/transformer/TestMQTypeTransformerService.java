package com.djuwidja.networktype.transformer;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.djuwidja.networktype.NTDict;
import com.djuwidja.networktype.transformer.MQTypeTransformerException;
import com.djuwidja.networktype.transformer.MQTypeTransformerService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.junit.Assert;

@ExtendWith(SpringExtension.class)
public class TestMQTypeTransformerService {
	private MQTypeTransformerService transformerService = new MQTypeTransformerService();
    
    @Test
    public void testSuccess() throws MQTypeTransformerException {
    	TestClass testObj = TestClass.buildSuccessTestObj();
    	
    	NTDict dict = transformerService.transformObj(testObj);
    	String dictJsonStr = dict.toJsonString();
    	Assert.assertEquals(testObj.toMQDict().toJsonString(), dictJsonStr);
    }
    
    @Test
    public void testEmpty() throws MQTypeTransformerException {
    	TestClass testObj = TestClass.buildEmptyTestObj();
    	
    	NTDict dict = transformerService.transformObj(testObj);
    	String dictJsonStr = dict.toJsonString();
    	Assert.assertEquals(testObj.toMQDict().toJsonString(), dictJsonStr);
    }
    
    @Test
    public void testWithNullField() throws MQTypeTransformerException {
    	TestClass testObj = TestClass.buildTestObjWithNulls();
    	
    	NTDict dict = transformerService.transformObj(testObj);
    	String dictJsonStr = dict.toJsonString();
    	Assert.assertEquals(testObj.toMQDict().toJsonString(), dictJsonStr);
    }
    
    @Test
    public void testNestedObj() throws MQTypeTransformerException {
    	NestedTestClass testObj = new NestedTestClass();
    	NTDict dict = transformerService.transformObj(testObj);
    	String dictJsonStr = dict.toJsonString();
    	Assert.assertEquals(testObj.toMQDict().toJsonString(), dictJsonStr);
    }
    
    @Test
    public void testJsonObj() throws MQTypeTransformerException, IOException {
    	byte[] jsonBytes = Files.readAllBytes(Paths.get("./src/test/unit/resources/TestJson.json"));
    	String content = new String(jsonBytes);
    	
    	JSONObject jsonObj = new JSONObject(content);
    	NTDict dict = transformerService.transformJson(jsonObj);
    	String strResult = dict.toJsonString().replace(" ", "");
    	String expected = jsonObj.toString();
    	
    	Assert.assertEquals(expected, strResult);
    	
    }
}
