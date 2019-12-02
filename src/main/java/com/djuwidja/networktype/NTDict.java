/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype;

import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.AbstractMap.SimpleEntry;
import java.util.Date;
/**
 * NTDict represents map interface.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class NTDict extends NTObject {
    private LinkedHashMap<String, SimpleEntry<NTObject,NTObject>> value;
    
    public NTDict(){
        super(NTObject.TYPE_DICT);
        value = new LinkedHashMap<String, SimpleEntry<NTObject,NTObject>>();
    }
    
    public NTDict(LinkedHashMap<String, SimpleEntry<NTObject,NTObject>> v){
        super(NTObject.TYPE_DICT);
        value = v;
    }
    
    public int size(){
        return value.size();
    }
    
    public NTObject getKeyObj(String key){
        NTObject result = null;
        SimpleEntry<NTObject, NTObject> entry = value.get(key);
        if (entry != null){
            result = entry.getKey();
        }
        
        return result;
    }
    
    public NTObject getValueObj(String key){
        NTObject result = null;
        SimpleEntry<NTObject, NTObject> entry = value.get(key);
        if (entry != null){
            result = entry.getValue();
        }
        
        return result;
    }
    
    public boolean containsKey(String key){
        return value.containsKey(key);
    }
    
    public NTObject get(String key){
        return getValueObj(key);
    }
    
    public boolean getBool(String key){
        return ((NTBool) getValueObj(key)).get();
    }
    
    public byte[] getBytes(String key){
        return ((NTByteArray) getValueObj(key)).get();
    }
    
    public Date getDate(String key){
        return ((NTDate) getValueObj(key)).get();
    }
    
    public NTDict getDict(String key){
        return (NTDict) getValueObj(key);
    }
    
    public float getFloat(String key){
        return ((NTFloat) getValueObj(key)).getFloat();
    }
    
    public double getDouble(String key) {
    	return ((NTFloat) getValueObj(key)).getDouble();
    }
    
    public short getShort(String key){
        return ((NTInt) getValueObj(key)).getShort();
    }
    
    public int getInt(String key){
        return ((NTInt) getValueObj(key)).getInt();
    }
    
    public long getLong(String key){
        return ((NTInt) getValueObj(key)).getLong();
    }
    
    public NTList getList(String key){
        return (NTList) getValueObj(key);
    }
    
    public String getString(String key){
        return ((NTString) getValueObj(key)).get();
    }
    
    public void put(NTObject key, NTObject value){
        SimpleEntry<NTObject, NTObject> entry = new SimpleEntry<NTObject, NTObject>(key, value);
        this.value.put(key.toDictKey(), entry);
    }
    
    public void put(String key, boolean value){
        put(new NTString(key), new NTBool(value));
    }
       
    public void put(String key, byte[] value){
        put(new NTString(key), new NTByteArray(value));
    }
    
    public void put(String key, Date value){
        put(new NTString(key), new NTDate(value));
    }
    
    public void put(String key, float value){
        put(new NTString(key), new NTFloat(value));
    }
    
    public void put(String key, short value){
        put(new NTString(key), new NTInt(value));
    }
    
    public void put(String key, int value){
        put(new NTString(key), new NTInt(value));
    }
    
    public void put(String key, long value){
        put(new NTString(key), new NTInt(value));
    }
        
    public void put(String key, String value){
        put(new NTString(key), new NTString(value));
    }
           
    public void put(String key, NTObject value){
        put(new NTString(key), value);
    }
    
    public boolean remove(String key) {
    	if (value.containsKey(key)) {
    		value.remove(key);
    		return true;
    	}
    	
    	return false;
    }
      
    public void clear(){
        value.clear();
    }
        
    public LinkedHashMap<String, SimpleEntry<NTObject,NTObject>> get(){
        return value;
    }
    
    public void set(LinkedHashMap<String, SimpleEntry<NTObject,NTObject>> v){
        value = v;
    }
    
    @Override
    public String toDictKey(){
        return toString();
    }
    
    @Override
    public String toString(){
        String str = "";
        Iterator<Entry<String, SimpleEntry<NTObject, NTObject>>> it = value.entrySet().iterator();
        boolean isFirst = true;
        while (it.hasNext()){
            if (!isFirst){
                str += ", ";
            }
            
            Map.Entry<String, SimpleEntry<NTObject, NTObject>> pair = (Entry<String, SimpleEntry<NTObject, NTObject>>)it.next();
            String key = pair.getKey();
            SimpleEntry<NTObject, NTObject> valuePair = pair.getValue();
            NTObject val = valuePair.getValue();
            str += String.format("\"%s\": %s", key, val.toString());
            
            isFirst = false;
        }
        
        return String.format("{%s}", str);
    }
    
    public String toJsonString(){
        String content = "";
        boolean isStart = true;      
        
        for (Map.Entry<String, SimpleEntry<NTObject,NTObject>> entry : value.entrySet()){
            if (!isStart){
                content += ",";
            }
            String key = entry.getKey();
            NTObject value = entry.getValue().getValue();
            content += String.format("\"%s\": %s", key, value.toJsonString());
            isStart = false;
        }
        
        return String.format("{%s}", content);
    }
    
    @Override
    public boolean equals(NTObject obj) {
    	if (!super.equals(obj)) {
    		return false;
    	}
    	
    	NTDict objDict = (NTDict) obj;
    	if (this.size() != objDict.size()) {
    		return false;
    	}    	
    	
    	Set<String> keySet = value.keySet();
    	for (String key : keySet) {
    		if (!objDict.containsKey(key)) {
    			return false;
    		}
    		
    		NTObject valueObj = this.get(key);
    		NTObject objDictObj = objDict.get(key);
    		
    		if (!valueObj.equals(objDictObj)) {
    			return false;
    		}
    	}
    	
    	return true;
    	
    }
}
