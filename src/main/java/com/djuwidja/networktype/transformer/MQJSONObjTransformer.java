package com.djuwidja.networktype.transformer;

import java.util.Set;

import org.json.JSONObject;

import com.djuwidja.networktype.NTDict;
import com.djuwidja.networktype.NTNull;
import com.djuwidja.networktype.NTObject;
/**
 * Transform a {@link JSONObject} into MQDict.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class MQJSONObjTransformer implements MQTypeTransformer {
	@Override
	public NTObject transform(Object data) throws IllegalAccessException {
		SupportedClassMapper classMapper = SupportedClassMapper.getInstance();
		
		NTDict result = new NTDict();
		
		JSONObject json = (JSONObject) data;
		Set<String> keySet = json.keySet();
		
		for (String key : keySet) {
			Object value = json.get(key);
			try {
				MQTypeTransformer transformer = classMapper.getTransformer(value.getClass());
				NTObject valueType = transformer.transform(value);
				result.put(key, valueType);				
			} catch (final SupportedClassMapperException e) {
				result.put(key, new NTNull());
			}				
		}
		
		return result;
	}
}
