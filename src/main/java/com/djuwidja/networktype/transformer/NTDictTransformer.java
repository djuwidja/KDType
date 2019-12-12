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
		NTDict dict = new NTDict();
        
        Map<?,?> dataMap = (Map<?,?>) data;
        for (Map.Entry<?,?> entry : dataMap.entrySet()){
            Object key = entry.getKey();
            Object value = entry.getValue();
            
            NTObject keyType = transformObject(key);
            NTObject valueType = transformObject(value);
            
            if (keyType.getType() == NTObject.TYPE_NULL || keyType.getType() == NTObject.TYPE_NULL) {
            	return new NTNull();
            }
                        
            dict.put(keyType, valueType);
        }
        
        return dict;
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
