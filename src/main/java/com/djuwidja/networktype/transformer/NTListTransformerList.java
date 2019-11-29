package com.djuwidja.networktype.transformer;

import java.util.List;

import com.djuwidja.networktype.NTList;
import com.djuwidja.networktype.NTNull;
import com.djuwidja.networktype.NTObject;
/**
 * Transform a {@link List} into {@link NTList}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class NTListTransformerList implements NTTransformer {
	
	@Override
	public NTObject transform(Object data) throws IllegalAccessException {
		SupportedClassMapper classMapper = SupportedClassMapper.getInstance();
		
		try {
			List<?> dataList = (List<?>) data;
	        if (dataList.size() > 0){
	            Object firstObj = dataList.get(0);
	            NTTransformer transformer = classMapper.getTransformer(firstObj.getClass());
	            if (transformer != null) {
	            	NTObject[] mqTypeList = new NTObject[dataList.size()];
	                for (int i = 0, size = mqTypeList.length; i < size; i++){
	                    Object currObj = dataList.get(i);
	                    mqTypeList[i] = transformer.transform(currObj);
	                }
	                
	                return new NTList(mqTypeList);
	            }
	        }
		}
		catch (final SupportedClassMapperException e) {

		}
		
		return new NTNull();
	}
}