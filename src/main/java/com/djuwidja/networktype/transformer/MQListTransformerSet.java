package com.djuwidja.networktype.transformer;

import java.util.Iterator;
import java.util.Set;

import com.djuwidja.networktype.NTList;
import com.djuwidja.networktype.NTNull;
import com.djuwidja.networktype.NTObject;
/**
 * Transform a {@link Set} into MQList.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class MQListTransformerSet implements MQTypeTransformer {
	@Override
	public NTObject transform(Object data) throws IllegalAccessException {
		SupportedClassMapper classMapper = SupportedClassMapper.getInstance();
		
		try {
			Set<?> dataList = (Set<?>) data;
	        Iterator<?> iter = dataList.iterator();
	        if (iter.hasNext()){
	            Object currObj = iter.next();
	            MQTypeTransformer transformer = classMapper.getTransformer(currObj.getClass());
	            if (transformer != null) {
	            	NTObject[] mqTypeList = new NTObject[dataList.size()];
	                int currIdx = 0;
	                while (true) {
	                    mqTypeList[currIdx] = transformer.transform(currObj);
	                    if (iter.hasNext()){
	                        currObj = iter.next();
	                        currIdx++;
	                    } else {
	                        break;
	                    }
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
