package com.djuwidja.networktype.transformer;

import java.util.Iterator;
import java.util.Set;

import com.djuwidja.networktype.NTList;
import com.djuwidja.networktype.NTNull;
import com.djuwidja.networktype.NTObject;
/**
 * Transform a {@link Set} into {@link NTList}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class NTListTransformerSet implements NTTransformer {
		
	@Override
	public NTObject transform(Object data) throws IllegalAccessException {
		Set<?> dataList = (Set<?>) data;
        Iterator<?> iter = dataList.iterator();
        NTObject[] mqTypeList = new NTObject[dataList.size()];
        int currIdx = 0;
        while (iter.hasNext()) {
        	Object currObj = iter.next();
        	NTObject ntObj = transformObject(currObj);
        	if (ntObj != null) {
        		mqTypeList[currIdx] = ntObj;
        		currIdx++;
        	} else {
        		return new NTNull();
        	}
        }
        
        return new NTList(mqTypeList);
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
