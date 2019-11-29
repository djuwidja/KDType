package com.djuwidja.networktype.serialization;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import com.djuwidja.networktype.NTBool;
import com.djuwidja.networktype.NTByteArray;
import com.djuwidja.networktype.NTDate;
import com.djuwidja.networktype.NTDict;
import com.djuwidja.networktype.NTFloat;
import com.djuwidja.networktype.NTInt;
import com.djuwidja.networktype.NTList;
import com.djuwidja.networktype.NTObject;
import com.djuwidja.networktype.NTString;
/**
 * A map of objects responsible to serialize a supported network type.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 *
 */
class SerializationFuncMapper {
	private static SerializationFuncMapper instance = null;
		
	private Map<Integer, SerializeFunc> funcMap = new HashMap<>(); 
	/**
	 * Get the instance of this singleton object.
	 * @return {@link SerializationFuncMapper} the instance.
	 */
	public static SerializationFuncMapper getInstance() {
		if (instance == null)
			instance = new SerializationFuncMapper();
		
		return instance;
	}
	/**
	 * Singleton class private constructor
	 */
	private SerializationFuncMapper() {
		funcMap.put(NTObject.TYPE_NULL, new SerializeNull());
		funcMap.put(NTObject.TYPE_BYTE_ARRAY, new SerializeByteArray());
		funcMap.put(NTObject.TYPE_INT, new SerializeInt());
		funcMap.put(NTObject.TYPE_STRING, new SerializeString());
		funcMap.put(NTObject.TYPE_BOOL, new SerializeBool());
		funcMap.put(NTObject.TYPE_FLOAT, new SerializeFloat());
		funcMap.put(NTObject.TYPE_DATETIME, new SerializeDatetime());
		funcMap.put(NTObject.TYPE_LIST, new SerializeList());
		funcMap.put(NTObject.TYPE_DICT, new SerializeDict());
	}
	/**
	 * Get the serializer with {@link NTObject}.
	 * @param type {@link NTObject}.
	 * @return Function to serialize the {@link NTObject}.
	 * @throws SerializerException fails when provided type is not supported.
	 */
	public SerializeFunc getSerializer(int type) throws SerializerException {
		if (funcMap.containsKey(type)) {
			return funcMap.get(type);
		}
		
		throw new SerializerException(String.format("MQtype not found %d", type));
	}	
}
/**
 * Serializer for MQNull type. See {@link SerializeFunc}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 *
 */
class SerializeNull implements SerializeFunc {
	@Override
	public SerializeResult execute(NTObject data) throws SerializerException {
        return new SerializeResult((byte) SerializationDataType.TYPE_NULL, new byte[0]);
    }
}
/**
 * Serializer for MQByteArray type. See {@link SerializeFunc}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 *
 */
class SerializeByteArray implements SerializeFunc {
	@Override
	public SerializeResult execute(NTObject data) throws SerializerException {
        byte[] result = SerializeHelper.getByteArrayBytes(((NTByteArray) data).get());
        return new SerializeResult((byte) SerializationDataType.TYPE_BYTE_ARRAY, result);
    }	
}
/**
 * Serializer for MQInt type. Including long, short and integer See {@link SerializeFunc}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 *
 */
class SerializeInt implements SerializeFunc {
	@Override
	public SerializeResult execute(NTObject data){
        return serializeInt((NTInt) data);
    }
	/**
	 * Serialize MQInt type by trimming the content into short, integer or long when necessary.
	 * @param data the MQType
	 * @return the SerializedResult
	 */
	private SerializeResult serializeInt(NTInt data){
        byte typeVal = SerializationDataType.TYPE_INT;        
        return new SerializeResult(typeVal, SerializeHelper.getByteArrayInt(data.getLong()));
    }
}
/**
 * Serializer for MQString type. See {@link SerializeFunc}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 *
 */
class SerializeString implements SerializeFunc {
	@Override
	public SerializeResult execute(NTObject data) throws SerializerException {               
        String val = ((NTString) data).get();                
        byte[] result = SerializeHelper.getByteArrayStr(val);
        return new SerializeResult((byte) SerializationDataType.TYPE_STRING, result);
    }
}
/**
 * Serializer for MQBool type. See {@link SerializeFunc}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 *
 */
class SerializeBool implements SerializeFunc {
	@Override
	public SerializeResult execute(NTObject data) throws SerializerException {
        boolean val = ((NTBool) data).get();
        
        byte[] byteVal = new byte[]{
            (byte) (val? 1 : 0)
        };
        return new SerializeResult((byte) SerializationDataType.TYPE_BOOL, byteVal);
	}
}
/**
 * Serializer for MQFloat type. See {@link SerializeFunc}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 *
 */
class SerializeFloat implements SerializeFunc {
	@Override
	public SerializeResult execute(NTObject data) throws SerializerException {
        double val = ((NTFloat) data).getDouble();
        String valStr = Double.toString(val);
        byte[] result = SerializeHelper.getByteArrayStr(valStr);
        return new SerializeResult((byte) SerializationDataType.TYPE_FLOAT, result);
    }
}
/**
 * Serializer for MQDate type. See {@link SerializeFunc}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 *
 */
class SerializeDatetime implements SerializeFunc {
	@Override
	public SerializeResult execute(NTObject data) throws SerializerException {
        Date val = ((NTDate) data).get();
        long timestamp = val.getTime();
        byte[] bytes = SerializeHelper.getByteArrayInt(timestamp);
        
        return new SerializeResult((byte) SerializationDataType.TYPE_DATETIME, bytes);
    }
}
/**
 * Serializer for MQList type. See {@link SerializeFunc}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 *
 */
class SerializeList implements SerializeFunc {
	@Override
	public SerializeResult execute(NTObject data) throws SerializerException {
		SerializationFuncMapper mapper = SerializationFuncMapper.getInstance();
		
        NTObject[] list = ((NTList) data).get();
        int listSize = list.length;
        
        byte[] result = new byte[1];
        
        byte[] lenBytes = SerializeHelper.getByteArrayInt(listSize);
        
        if (listSize == 0) {
        	result[0] = SerializationDataType.TYPE_NULL;
        } else {
        	boolean hasWrittenType = false;
            for (int i = 0; i < listSize; i++){
                NTObject compType = list[i];
                SerializeFunc func = mapper.getSerializer(compType.getType());
                SerializeResult comResult = func.execute(compType);
                
                if (!hasWrittenType){
                    result[0] = comResult.getType();
                    hasWrittenType = true;
                }

                result = SerializeHelper.mergeByteArray(result, comResult.getBytes());
            }
        }

        return new SerializeResult((byte) SerializationDataType.TYPE_LIST, SerializeHelper.mergeByteArray(lenBytes, result));
    }
}
/**
 * Serializer for MQDict type. See {@link SerializeFunc}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 *
 */
class SerializeDict implements SerializeFunc {
	@Override
	public SerializeResult execute(NTObject data) throws SerializerException {
		SerializationFuncMapper mapper = SerializationFuncMapper.getInstance();
		
        HashMap<String, SimpleEntry<NTObject, NTObject>> map = ((NTDict) data).get();
        
        Iterator<Entry<String, SimpleEntry<NTObject, NTObject>>> it = map.entrySet().iterator();
        
        SerializationBuffer bf = new SerializationBuffer();
        bf.writeBytes(SerializeHelper.getByteArrayInt(map.size()));
        while (it.hasNext()){                   
             Map.Entry<String, SimpleEntry<NTObject, NTObject>> pair = (Map.Entry<String, SimpleEntry<NTObject, NTObject>>)it.next();
             SimpleEntry<NTObject, NTObject> valuePair = pair.getValue();
             NTObject key = valuePair.getKey();
             NTObject val = valuePair.getValue();
             SerializeFunc keyFunc = mapper.getSerializer(key.getType());
             SerializeFunc valFunc = mapper.getSerializer(val.getType());
             
             SerializeResult keyResult = keyFunc.execute(key);
             bf.writeBytes(keyResult.getArray());
             SerializeResult valResult = valFunc.execute(val);
             bf.writeBytes(valResult.getArray());                    
        }
        
        byte[] bfArr = bf.getArray();               
        return new SerializeResult((byte) SerializationDataType.TYPE_DICT, bfArr);
    }
}