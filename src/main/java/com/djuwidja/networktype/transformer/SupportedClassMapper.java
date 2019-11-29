package com.djuwidja.networktype.transformer;

import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 * Retrieve the transformer class for supported data type. Currently supports the following:
 * <p>
 * primitive types: boolean, double, float, short, int, long.
 * <p>
 * objects: {@link Boolean}, {@link Double}, {@link Float}, {@link Short}, {@link Integer}, 
 * 			{@link Long}, {@link String}, {@link Set}, {@link List}, {@link Map}, 
 * 			{@link JSONObject}, {@link JSONArray}
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
class SupportedClassMapper {
	private static SupportedClassMapper instance = null;
		
	private HashMap<Class<?>, NTTransformer> supportedDataTypeMap = new HashMap<>();
	/**
	 * Get the instance of this singleton object.
	 * @return {@link SupportedClassMapper} the instance.
	 */
	public static SupportedClassMapper getInstance() {
		if (instance == null)
			instance = new SupportedClassMapper();
		
		return instance;
	}
	/**
	 * Singleton private constructor.
	 */
	private SupportedClassMapper() {
		supportedDataTypeMap.put(boolean.class, new NTBoolTransformer());
		supportedDataTypeMap.put(Boolean.class, new NTBoolTransformer());
		supportedDataTypeMap.put(Date.class, new NTDateTransformer());
		supportedDataTypeMap.put(double.class, new NTDoubleTransformer());
		supportedDataTypeMap.put(Double.class, new NTDoubleTransformer());
		supportedDataTypeMap.put(float.class, new NTFloatTransformer());
		supportedDataTypeMap.put(Float.class, new NTFloatTransformer());
		supportedDataTypeMap.put(short.class, new NTIntTransformerShort());
		supportedDataTypeMap.put(Short.class, new NTIntTransformerShort());
		supportedDataTypeMap.put(int.class, new NTIntTransformerInt());
		supportedDataTypeMap.put(Integer.class, new NTIntTransformerInt());
		supportedDataTypeMap.put(long.class, new NTIntTransformerLong());
		supportedDataTypeMap.put(Long.class, new NTIntTransformerLong());
		supportedDataTypeMap.put(String.class, new NTStringTransformer());
		supportedDataTypeMap.put(ObjectId.class, new NTObjectIdTransformer());
		supportedDataTypeMap.put(Set.class, new NTListTransformerSet());
		supportedDataTypeMap.put(List.class, new NTListTransformerList());
		supportedDataTypeMap.put(Map.class, new NTDictTransformer());
		supportedDataTypeMap.put(JSONObject.class, new NTJSONObjTransformer());
		supportedDataTypeMap.put(JSONArray.class, new NTJSONArrayTransformer());
	}
	/**
	 * Get transformer from the provided class. Will also attempt to get a transformer from interfaces 
	 * and class hierarchy if none is found for the provided class.
	 * <p>
	 * Please see {@link SupportedClassMapper#getTransformerFromInterfaces(Class)} and
	 * 			  {@link SupportedClassMapper#getTransformerFromClassHierarchy(Class)}.
	 * @param cls class to get transformer.
	 * @return The transformer of the corresponding class.
	 * @throws SupportedClassMapperException fails when no transformer can be found.
	 */
	public NTTransformer getTransformer(Class<?> cls) throws SupportedClassMapperException {
		if (supportedDataTypeMap.containsKey(cls)) {
			return supportedDataTypeMap.get(cls);
		}
		
		try {
			return getTransformerFromInterfaces(cls);
		}
		catch (final SupportedClassMapperException e) {
			return getTransformerFromClassHierarchy(cls);
		}
	}
	/**
	 * Get a transformer from interfaces. Only 1 transformer is supported at this time.
	 * @param cls class to get transformer.
	 * @return The transformer from one of the interfaces.
	 * @throws SupportedClassMapperException fails when no transformer can be found.
	 */
	private NTTransformer getTransformerFromInterfaces(Class<?> cls) throws SupportedClassMapperException {
		Class<?>[] interfaces = cls.getInterfaces();
		for (Class<?> i : interfaces) {
			if (supportedDataTypeMap.containsKey(i)) {
				return supportedDataTypeMap.get(i);
			}
		}
		
		throw new SupportedClassMapperException(String.format("Interface type not supported: %s", cls.getName()));
	}
	/**
	 * Get a transformer from class hierarchy. Only 1 transformer is supported at this time.
	 * @param cls class to get transformer.
	 * @return The transformer from the class hierarchy.
	 * @throws SupportedClassMapperException fails when no transformer can be found.
	 */
	private NTTransformer getTransformerFromClassHierarchy(Class<?> cls) throws SupportedClassMapperException {
		Class<?> currCls = cls;
		while (currCls != null && !supportedDataTypeMap.containsKey(currCls)) {
			currCls = currCls.getSuperclass();
		}
		
		if (currCls != null) {
			return supportedDataTypeMap.get(currCls);
		}
		
		throw new SupportedClassMapperException(String.format("Class Hierarchy type not supported: %s", cls.getName()));
	}
}
