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
    /**
     * Constructs an empty NTDict.
     */
    public NTDict(){
        super(NTObject.TYPE_DICT);
        value = new LinkedHashMap<String, SimpleEntry<NTObject,NTObject>>();
    }
    /**
     * Constructs a NTDict with a LinkedHashMap.
     * @param v - {@link LinkedHashMap} the contents.
     */
    public NTDict(LinkedHashMap<String, SimpleEntry<NTObject,NTObject>> v){
        super(NTObject.TYPE_DICT);
        value = v;
    }
    /**
     * Returns the size of this NTDict.
     * @return int - the size of this NTDict.
     */
    public int size(){
        return value.size();
    }
    /**
     * Returns the keySet of this NTDict.
     * @return {@link Set}<{@link String}> - the key set of this NTDict.
     */
    public Set<String> keySet() {
    	return value.keySet();
    }
    /**
     * Returns the key object based on param.
     * @param key - {@link String}
     * @return {@link NTObject}.
     * @throws KeyNotFoundException if provided key does not exist in this NTDict.
     */
    public NTObject getKeyObj(String key) throws KeyNotFoundException {
        NTObject result = null;
        SimpleEntry<NTObject, NTObject> entry = value.get(key);
        if (entry == null) {
        	throw new KeyNotFoundException(String.format("Key not found: %s", key));
        }

        result = entry.getKey();
        return result;
    }
    /**
     * Returns the value based on param.
     * @param key - {@link String}
     * @return {@link NTObject}.
     * @throws KeyNotFoundException if provided key does not exist in this NTDict.
     */
    public NTObject getValueObj(String key) throws KeyNotFoundException {
        NTObject result = null;
        SimpleEntry<NTObject, NTObject> entry = value.get(key);
        if (entry == null) {
        	throw new KeyNotFoundException(String.format("Key not found: %s", key));
        }

        result = entry.getValue();
        return result;
    }
    /**
     * Check if key exists in this NTDict.
     * @param key - {@link String}
     * @return boolean - true if key exists, false otherwise.
     */
    public boolean containsKey(String key){
        return value.containsKey(key);
    }
    /**
     * Invokes {@link NTDict#getValueObj(String)} and return its result.
     * @param key - {@link String}
     * @return {@link NTObject}. Null if none is found.
     * @throws KeyNotFoundException if provided key does not exist in this NTDict.
     */
    public NTObject get(String key) throws KeyNotFoundException {
        return getValueObj(key);
    }
    /**
     * Invokes {@link NTDict#getValueObj(String)} and return its result as boolean.
     * @param key - {@link String}
     * @return boolean.
     * @throws KeyNotFoundException if provided key does not exist in this {@link NTDict}.
     * @throws ClassCastException if the value of NTObject is not a boolean.
     */
    public boolean getBool(String key) throws KeyNotFoundException, ClassCastException {
        return ((NTBool) getValueObj(key)).get();
    }
    /**
     * Invokes {@link NTDict#getValueObj(String)} and return its as byte array.
     * @param key - {@link String}
     * @return byte[].
     * @throws KeyNotFoundException if provided key does not exist in this {@link NTDict}.
     * @throws ClassCastException if the value of NTObject is not a byte array.
     */
    public byte[] getBytes(String key) throws KeyNotFoundException, ClassCastException {
        return ((NTByteArray) getValueObj(key)).get();
    }
    /**
     * Invokes {@link NTDict#getValueObj(String)} and return its as {@link Date}.
     * @param key - {@link String}
     * @return {@link Date}.
     * @throws KeyNotFoundException if provided key does not exist in this {@link NTDict}.
     * @throws ClassCastException if the value of NTObject is not a {@link Date}.
     */
    public Date getDate(String key) throws KeyNotFoundException, ClassCastException {
        return ((NTDate) getValueObj(key)).get();
    }
    /**
     * Invokes {@link NTDict#getValueObj(String)} and return its as {@link NTDict}.
     * @param key - {@link String}
     * @return {@link NTDict}.
     * @throws KeyNotFoundException if provided key does not exist in this {@link NTDict}.
     * @throws ClassCastException if the value of NTObject is not a {@link NTDict}.
     */
    public NTDict getDict(String key) throws KeyNotFoundException, ClassCastException {
        return (NTDict) getValueObj(key);
    }
    /**
     * Invokes {@link NTDict#getValueObj(String)} and return its as float.
     * @param key - {@link String}
     * @return float.
     * @throws KeyNotFoundException if provided key does not exist in this {@link NTDict}.
     * @throws ClassCastException if the value of NTObject is not a float.
     */
    public float getFloat(String key) throws KeyNotFoundException, ClassCastException {
        return ((NTFloat) getValueObj(key)).getFloat();
    }
    /**
     * Invokes {@link NTDict#getValueObj(String)} and return its as double.
     * @param key - {@link String}
     * @return double.
     * @throws KeyNotFoundException if provided key does not exist in this {@link NTDict}.
     * @throws ClassCastException if the value of NTObject is not a double.
     */
    public double getDouble(String key) throws KeyNotFoundException, ClassCastException {
    	return ((NTFloat) getValueObj(key)).getDouble();
    }
    /**
     * Invokes {@link NTDict#getValueObj(String)} and return its as short.
     * @param key - {@link String}
     * @return short.
     * @throws KeyNotFoundException if provided key does not exist in this {@link NTDict}.
     * @throws ClassCastException if the value of NTObject is not a short.
     */
    public short getShort(String key) throws KeyNotFoundException, ClassCastException {
        return ((NTInt) getValueObj(key)).getShort();
    }
    /**
     * Invokes {@link NTDict#getValueObj(String)} and return its as int.
     * @param key - {@link String}
     * @return int.
     * @throws KeyNotFoundException if provided key does not exist in this {@link NTDict}.
     * @throws ClassCastException if the value of NTObject is not a int.
     */
    public int getInt(String key) throws KeyNotFoundException, ClassCastException {
        return ((NTInt) getValueObj(key)).getInt();
    }
    /**
     * Invokes {@link NTDict#getValueObj(String)} and return its as long.
     * @param key - {@link String}
     * @return long.
     * @throws KeyNotFoundException if provided key does not exist in this {@link NTDict}.
     * @throws ClassCastException if the value of NTObject is not a long.
     */
    public long getLong(String key) throws KeyNotFoundException, ClassCastException {
        return ((NTInt) getValueObj(key)).getLong();
    }
    /**
     * Invokes {@link NTDict#getValueObj(String)} and return its as {@link NTList}.
     * @param key - {@link String}
     * @return {@link NTList}.
     * @throws KeyNotFoundException if provided key does not exist in this {@link NTDict}.
     * @throws ClassCastException if the value of NTObject is not a {@link NTList}.
     */
    public NTList getList(String key) throws KeyNotFoundException, ClassCastException {
        return (NTList) getValueObj(key);
    }
    /**
     * Invokes {@link NTDict#getValueObj(String)} and return its as {@link String}.
     * @param key - {@link String}
     * @return {@link String}.
     * @throws KeyNotFoundException if provided key does not exist in this {@link NTDict}.
     * @throws ClassCastException if the value of NTObject is not a {@link String}.
     */
    public String getString(String key) throws KeyNotFoundException, ClassCastException {
        return ((NTString) getValueObj(key)).get();
    }
    /**
     * Put a key value pair of {@link NTObject} into this NTDict.
     * @param key - {@link NTObject} the key object.
     * @param value - {@link NTObject} the value object.
     */
    public void put(NTObject key, NTObject value){
        SimpleEntry<NTObject, NTObject> entry = new SimpleEntry<NTObject, NTObject>(key, value);
        this.value.put(key.toDictKey(), entry);
    }
    /**
     * Put a key value pair of ({@link String}, boolean) into this {@link NTDict}. This function will first
     * create a {@link NTString} base on the key and a {@link NTBool} base on the value and then
     * invoke {@link NTDict#put(NTObject, NTObject)}.
     * @param key - {@link String} the key.
     * @param value boolean - the value.
     */
    public void put(String key, boolean value){
        put(new NTString(key), new NTBool(value));
    }
    /**
     * Put a key value pair of ({@link String}, byte[]) into this {@link NTDict}. This function will first
     * create a {@link NTString} base on the key and a {@link NTByteArray} base on the value and then
     * invoke {@link NTDict#put(NTObject, NTObject)}.
     * @param key - {@link String} the key.
     * @param value byte[] - the value.
     */
    public void put(String key, byte[] value){
        put(new NTString(key), new NTByteArray(value));
    }
    /**
     * Put a key value pair of ({@link String}, {@link Date}) into this {@link NTDict}. This function will first
     * create a {@link NTString} base on the key and a {@link NTDate} base on the value and then
     * invoke {@link NTDict#put(NTObject, NTObject)}.
     * @param key - {@link String} the key.
     * @param value {@link Date} - the value.
     */
    public void put(String key, Date value){
        put(new NTString(key), new NTDate(value));
    }
    /**
     * Put a key value pair of ({@link String}, float) into this {@link NTDict}. This function will first
     * create a {@link NTString} base on the key and a {@link NTFloat} base on the value and then
     * invoke {@link NTDict#put(NTObject, NTObject)}.
     * @param key - {@link String} the key.
     * @param value float - the value.
     */
    public void put(String key, float value){
        put(new NTString(key), new NTFloat(value));
    }
    /**
     * Put a key value pair of ({@link String}, short) into this {@link NTDict}. This function will first
     * create a {@link NTString} base on the key and a {@link NTInt} base on the value and then
     * invoke {@link NTDict#put(NTObject, NTObject)}.
     * @param key - {@link String} the key.
     * @param value short - the value.
     */
    public void put(String key, short value){
        put(new NTString(key), new NTInt(value));
    }
    /**
     * Put a key value pair of ({@link String}, int) into this {@link NTDict}. This function will first
     * create a {@link NTString} base on the key and a {@link NTInt} base on the value and then
     * invoke {@link NTDict#put(NTObject, NTObject)}.
     * @param key - {@link String} the key.
     * @param value int - the value.
     */
    public void put(String key, int value){
        put(new NTString(key), new NTInt(value));
    }
    /**
     * Put a key value pair of ({@link String}, long) into this {@link NTDict}. This function will first
     * create a {@link NTString} base on the key and a {@link NTInt} base on the value and then
     * invoke {@link NTDict#put(NTObject, NTObject)}.
     * @param key - {@link String} the key.
     * @param value long - the value.
     */
    public void put(String key, long value){
        put(new NTString(key), new NTInt(value));
    }
    /**
     * Put a key value pair of ({@link String}, {@link String}) into this {@link NTDict}. This function will first
     * create a {@link NTString} base on the key and a {@link NTString} base on the value and then
     * invoke {@link NTDict#put(NTObject, NTObject)}.
     * @param key - {@link String} the key.
     * @param value {@link String} - the value.
     */
    public void put(String key, String value){
        put(new NTString(key), new NTString(value));
    }
    /**
     * Put a key value pair of ({@link String}, {@link NTObject}) into this {@link NTDict}. This function will first
     * create a {@link NTString} base on the key and then invoke {@link NTDict#put(NTObject, NTObject)}.
     * @param key - {@link String} the key.
     * @param value {@link NTObject} - the value.
     */
    public void put(String key, NTObject value){
        put(new NTString(key), value);
    }
    /**
     * Remove a key from this NTDict.
     * @param key - {@link String} they key.
     * @throws KeyNotFoundException if provided key does not exist in this NTDict.
     */
    public void remove(String key) throws KeyNotFoundException {
    	if (!value.containsKey(key)) {
    		throw new KeyNotFoundException(String.format("Key not found: %s", key));
    	}

    	value.remove(key);
    }
    /**
     * Clear this NTDict.
     */
    public void clear(){
        value.clear();
    }
    /**
     * Get the underlying map of this NTDict.
     * @return {@link LinkedHashMap}<{@link String},{@link SimpeEntry}<{@link NTObject},{@link NTObject}>> the underlying map.
     */
    public LinkedHashMap<String, SimpleEntry<NTObject,NTObject>> get(){
        return value;
    }
    /**
     * Set the underlying map of this NTDict.
     * @param v - {@link LinkedHashMap}<{@link String},{@link SimpeEntry}<{@link NTObject},{@link NTObject}>> the map to be set.
     */
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

    	try {
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
    	catch (final KeyNotFoundException e) {
    		return false;
    	}
    }
}
