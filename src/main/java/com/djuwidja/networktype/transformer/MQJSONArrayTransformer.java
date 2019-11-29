package com.djuwidja.networktype.transformer;

import java.util.List;

import org.json.JSONArray;

import com.djuwidja.networktype.NTList;
import com.djuwidja.networktype.NTObject;
/**
 * Transform a {@link JSONArray} into MQList.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class MQJSONArrayTransformer implements MQTypeTransformer {
	@Override
	public NTObject transform(Object data) throws IllegalAccessException {
		SupportedClassMapper classMapper = SupportedClassMapper.getInstance();
		
		JSONArray jsonArray = (JSONArray) data;
		List<Object> jsonList = jsonArray.toList();
		try {
			MQTypeTransformer transformer = classMapper.getTransformer(jsonList.getClass());
			return transformer.transform(jsonList);
		}
		catch (final SupportedClassMapperException e) {
			
		}
		return new NTList(new NTObject[0]);
	}
}
