package com.djuwidja.networktype.transformer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.djuwidja.networktype.NTDict;
import com.djuwidja.networktype.NTInt;
import com.djuwidja.networktype.NTList;
import com.djuwidja.networktype.NTNull;
import com.djuwidja.networktype.NTObject;

public class NestedTestClass {
	public TestClass innerObj;
	public Map<Integer, TestClass> nestedMap;
	public Set<TestClass> nestedSet;
	public List<TestClass> nestedList;
	
	public static NestedTestClass buildSuccessTestObj() {
		Random random = new Random(new Date().getTime());
		
		NestedTestClass obj = new NestedTestClass();
		obj.innerObj = TestClass.buildSuccessTestObj();
        obj.nestedMap = populateMap(random);
        obj.nestedSet = populateSet(random);
        obj.nestedList = populateList(random);
        
        return obj;
    }
    
    public static NestedTestClass buildEmptyTestObj() {
        return new NestedTestClass();
    }
    
    public static NestedTestClass buildTestObjWithNulls() {		
    	Random random = new Random(new Date().getTime());
    	
		NestedTestClass obj = new NestedTestClass();
		obj.innerObj = TestClass.buildTestObjWithNulls();
        obj.nestedMap = null;
        obj.nestedSet = populateSet(random);
        obj.nestedList = null;
        
        return obj;
    }
	
	public NTDict toNTDict() {
		NTDict dict = new NTDict();
		if (innerObj == null) {
			dict.put("innerObj", new NTNull());
		} else {
			dict.put("innerObj", innerObj.toNTDict());
		}
		
		if (nestedMap == null) {
			dict.put("nestedMap", new NTNull());
		} else {
			NTDict nestedMapDict = new NTDict();
			for (Map.Entry<Integer, TestClass> entry : nestedMap.entrySet()) {
				nestedMapDict.put(new NTInt(entry.getKey()), entry.getValue().toNTDict());
			}
			
			dict.put("nestedMap", nestedMapDict);
		}
		
		if (nestedSet == null) {
			dict.put("nestedSet", new NTNull());
		} else {
			NTObject[] objList = new NTObject[nestedSet.size()];
			
			int idx = 0;
			for (TestClass obj : nestedSet) {
				objList[idx] = obj.toNTDict();
				idx++;
			}
			
			dict.put("nestedSet", new NTList(objList));
		}
		
		if (nestedList == null) {
			dict.put("nestedList", new NTNull());
		} else {
			NTObject[] objList = new NTObject[nestedList.size()];
			
			int idx = 0;
			for (TestClass obj : nestedList) {
				objList[idx] = obj.toNTDict();
				idx++;
			}
			
			dict.put("nestedList", new NTList(objList));
		}
		
		return dict;
	}
	
	private static TestClass createTestClass(int type) {
		TestClass innerObj = null;
    	switch (type) {
    	case 0:
    		innerObj = TestClass.buildSuccessTestObj();
    		break;
    	case 1:
    		innerObj = TestClass.buildEmptyTestObj();
    		break;
    	case 2:
    		innerObj = TestClass.buildTestObjWithNulls();
    		break;
    	}
    	
    	return innerObj;
	}
	
	private static Map<Integer, TestClass> populateMap(Random randomizer){
		Map<Integer, TestClass> result = new HashMap<>();
		
		int numMap = randomizer.nextInt(10);  
        for (int i = 0; i < numMap; i++) {
        	int rand = randomizer.nextInt(3);
        	result.put(i, createTestClass(rand));
        }
        
        return result;
	}
	
	private static Set<TestClass> populateSet(Random randomizer){
		Set<TestClass> result = new HashSet<>();
		
		int numMap = randomizer.nextInt(10);  
        for (int i = 0; i < numMap; i++) {
        	int rand = randomizer.nextInt(3);
        	result.add(createTestClass(rand));
        }
        
        return result;
	}
	
	private static List<TestClass> populateList(Random randomizer){
		List<TestClass> result = new ArrayList<>();
		
		int numMap = randomizer.nextInt(10);  
        for (int i = 0; i < numMap; i++) {
        	int rand = randomizer.nextInt(3);
        	result.add(createTestClass(rand));
        }
        
        return result;
	}
}
