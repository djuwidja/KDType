package com.djuwidja.networktype.transformer;

import java.util.Map;

import com.djuwidja.networktype.NTDict;
import com.djuwidja.networktype.NTNull;
import com.djuwidja.networktype.NTObject;
/**
 * Transform a map into {@link NTDict}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class NTDictTransformer implements NTTransformer {
	@Override
	public NTObject transform(Object data) throws IllegalAccessException {
		SupportedClassMapper classMapper = SupportedClassMapper.getInstance();
		
		NTDict dict = new NTDict();
        
        Map<?,?> dataMap = (Map<?,?>) data;
        for (Map.Entry<?,?> entry : dataMap.entrySet()){
            Object key = entry.getKey();
            Object value = entry.getValue();
            
            try {
            	NTTransformer keyTransformer = classMapper.getTransformer(key.getClass());
                NTTransformer valTransformer = classMapper.getTransformer(value.getClass());
 
                NTObject keyType = keyTransformer.transform(key);
                NTObject valueType = valTransformer.transform(value);
                
                dict.put(keyType, valueType);

            }
            catch (final SupportedClassMapperException e) {
            	return new NTNull();
            }   
        }
        
        return dict;
	}
}
