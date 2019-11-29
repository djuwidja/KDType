package com.djuwidja.networktype.transformer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import com.djuwidja.networktype.NTDict;
import com.djuwidja.networktype.NTList;
import com.djuwidja.networktype.NTNull;

public class TestClass {
	public String testString;
	public short testShort;
	public int testInt;
	public long testLong;
	public float testFloat;
	public Date testDate;
	public boolean testBool;
	public List<Integer> testIntList;
	public Set<String> testStrSet;
	public Map<String, Integer> testStrIntMap;
	
	public NTDict toMQDict() {
		NTDict dict = new NTDict();
		if (testString != null) {
			dict.put("testString", testString);
		} else {
			dict.put("testString", new NTNull());
		}

		dict.put("testShort", testShort);
		dict.put("testInt", testInt);
		dict.put("testLong", testLong);
		dict.put("testFloat", testFloat);
		
		if (testDate != null) {
			dict.put("testDate", testDate);
		} else {
			dict.put("testDate", new NTNull());
		}
		
		dict.put("testBool", testBool);
						
		if (testIntList != null) {
			int[] intList = new int[testIntList.size()];
			for (int i = 0; i < testIntList.size(); i++) {
				intList[i] = testIntList.get(i);
			}				
			dict.put("testIntList", new NTList(intList));
		} else {
			dict.put("testIntList", new NTNull());
		}
	
		
		if (testStrSet != null) {
			String[] strList = new String[testStrSet.size()];
			int idx = 0;
			for (String str : testStrSet) {
				strList[idx] = str;
				idx++;
			}
			
			dict.put("testStrSet", new NTList(strList));
		} else {
			dict.put("testStrSet", new NTNull());
		}
		
		if (testStrIntMap != null) {
			NTDict testStrIntDict = new NTDict();
			for (Map.Entry<String, Integer> entry : testStrIntMap.entrySet()) {
				testStrIntDict.put(entry.getKey(), entry.getValue());
			}
			dict.put("testStrIntMap", testStrIntDict);
		} else {
			dict.put("testStrIntMap", new NTNull());
		}
		
		return dict;
	}
		
	public static TestClass buildSuccessTestObj() {
		TestClass obj = new TestClass();
		obj.testString = "testString";
		obj.testShort = Short.MAX_VALUE;
		obj.testInt = Integer.MAX_VALUE;
		obj.testLong = Long.MAX_VALUE;
		obj.testFloat = 3.141592654f;
		
		Calendar.Builder calendarBuilder = new Calendar.Builder();
		calendarBuilder.setCalendarType("gregorian");
		calendarBuilder.set(Calendar.YEAR, 2019);
		calendarBuilder.set(Calendar.MONTH, 10);
		calendarBuilder.set(Calendar.DAY_OF_MONTH, 30);
		calendarBuilder.set(Calendar.HOUR_OF_DAY, 0);
		calendarBuilder.set(Calendar.SECOND, 0);
		calendarBuilder.set(Calendar.MILLISECOND, 0);
		calendarBuilder.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		obj.testDate = calendarBuilder.build().getTime();
		obj.testBool = false;
		
		obj.testIntList = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			obj.
			testIntList.add(i);
		}
		obj.testStrSet = new HashSet<String>();
		for (int i = 0; i < 10; i++) {
			obj.testStrSet.add(Integer.toString(i));
		}
		obj.testStrIntMap = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			obj.testStrIntMap.put(Integer.toString(i), i);
		}
		
		return obj;
	}
	
	public static TestClass buildEmptyTestObj() {
		return new TestClass();
	}
	
	public static TestClass buildTestObjWithNulls() {
		TestClass obj = new TestClass();
		obj.testString = "testString";
		obj.testShort = Short.MAX_VALUE;
		obj.testLong = Long.MAX_VALUE;
		
		obj.testBool = false;
		
		obj.testIntList = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			obj.
			testIntList.add(i);
		}
		obj.testStrSet = new HashSet<String>();
		for (int i = 0; i < 10; i++) {
			obj.testStrSet.add(Integer.toString(i));
		}
		
		return obj;
	}	
}
