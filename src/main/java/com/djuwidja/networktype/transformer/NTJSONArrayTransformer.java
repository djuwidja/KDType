package com.djuwidja.networktype.transformer;

import java.util.List;

import org.json.JSONArray;

import com.djuwidja.networktype.NTList;
import com.djuwidja.networktype.NTObject;
/**
 * Transform a {@link JSONArray} into {@link NTList}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class NTJSONArrayTransformer implements NTTransformer {
	@Override
	public NTObject transform(Object data) throws IllegalAccessException {
		SupportedClassMapper classMapper = SupportedClassMapper.getInstance();
		
		JSONArray jsonArray = (JSONArray) data;
		List<Object> jsonList = jsonArray.toList();
		try {
			NTTransformer transformer = classMapper.getTransformer(jsonList.getClass());
			return transformer.transform(jsonList);
		}
		catch (final SupportedClassMapperException e) {
			
		}
		return new NTList(new NTObject[0]);
	}
}
