package com.djuwidja.networktype.serialization;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.djuwidja.networktype.NTDict;
import com.djuwidja.networktype.NTList;
import com.djuwidja.networktype.NTNull;
import com.djuwidja.networktype.NTObject;
import com.djuwidja.networktype.serialization.Deserializer;
import com.djuwidja.networktype.serialization.DeserializerException;
import com.djuwidja.networktype.serialization.Serializer;
import com.djuwidja.networktype.serialization.SerializerException;

import org.junit.Assert;

@ExtendWith(SpringExtension.class)
public class TestSerializer {
	
	private Serializer serializer = new Serializer();
	private Deserializer deserializer = new Deserializer();
	
	@Test
	public void testSerialize() throws SerializerException, DeserializerException {
		NTDict testData = new NTDict();
		testData.put("short", Short.MAX_VALUE);
		testData.put("int", Integer.MAX_VALUE);
		testData.put("long", Long.MAX_VALUE);
		testData.put("bool", false);
		
		Calendar.Builder builder = new Calendar.Builder();
		builder.setDate(2019, 10, 24);
		builder.setTimeOfDay(0, 0, 0);
		
		Date date = new Date(builder.build().getTimeInMillis());		
		testData.put("date", date);
		testData.put("string", "asdfasdfasdf");
		testData.put("null", new NTNull());
		testData.put("float", 3.1415f);
		
		String[] list = new String[5];
		for (int i = 0; i < list.length; i++) {
			list[i] = "abcd";
		}
		
		testData.put("list", new NTList(list));
		
		NTDict subDict = new NTDict();
		subDict.put("asdf", "asdfasdf");
		testData.put("dict", subDict);
		
		runTest(testData);
	}
	
	@Test
	public void testEmptyList() throws SerializerException, DeserializerException {
		NTDict testData = new NTDict();
		
		String[] strList = new String[0];
		NTList mqList = new NTList(strList);
		testData.put("strList", mqList);
		
		runTest(testData);
	}
	
	@Test
	public void testEmptyMap() throws SerializerException, DeserializerException {
		NTDict testData = new NTDict();
		NTDict emptyDict = new NTDict();
		testData.put("emptyDict", emptyDict);
		
		runTest(testData);
	}
	
	private void runTest(NTDict testData) throws SerializerException, DeserializerException {
		byte[] sResult = serializer.serialize(testData);
		NTObject deResult = deserializer.deserialize(sResult);
		
		Assert.assertEquals(testData.toJsonString(), deResult.toJsonString());
	}

}
