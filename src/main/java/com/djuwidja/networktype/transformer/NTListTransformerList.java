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
		List<?> dataList = (List<?>) data;
        if (dataList.size() > 0){
            Object firstObj = dataList.get(0);
            
            NTObject transformed = transformObject(firstObj);
            if (transformed.getType() != NTObject.TYPE_NULL) {
            	NTObject[] mqTypeList = new NTObject[dataList.size()];
            	mqTypeList[0] = transformed;
            	for (int i = 1, size = mqTypeList.length; i < size; i++){
                    Object currObj = dataList.get(i);
                    mqTypeList[i] = transformObject(currObj);          
                }
            	
            	return new NTList(mqTypeList);
            }
        }
		
		
		return new NTNull();
	}
	
	private NTObject transformObject(Object obj) throws IllegalAccessException {
		SupportedClassMapper classMapper = SupportedClassMapper.getInstance();
		try {
			NTTransformer transformer = classMapper.getTransformer(obj.getClass());
			NTObject objType = transformer.transform(obj);
			return objType;
		}
		catch (final SupportedClassMapperException spce) {
			try {
				return NTObjectTransformerInternal.getInstance().transformObj(obj);
			}
			catch (final NTObjectTransformerException nte) {
				
			}
		}
		
		return new NTNull();
	}
}