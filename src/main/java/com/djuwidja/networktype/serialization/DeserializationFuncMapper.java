package com.djuwidja.networktype.serialization;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

import com.djuwidja.networktype.NTBool;
import com.djuwidja.networktype.NTByteArray;
import com.djuwidja.networktype.NTDate;
import com.djuwidja.networktype.NTDict;
import com.djuwidja.networktype.NTFloat;
import com.djuwidja.networktype.NTInt;
import com.djuwidja.networktype.NTList;
import com.djuwidja.networktype.NTNull;
import com.djuwidja.networktype.NTObject;
import com.djuwidja.networktype.NTString;
/**
 * A map of objects responsible to deserialize a supported network type.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 *
 */
class DeserializationFuncMapper {
	private static DeserializationFuncMapper instance = null;	
	
	private Map<Integer, DeserializeFunc> funcMap = new HashMap<>();
	/**
	 * Get the instance of this singleton object.
	 * @return {@link DeserializationFuncMapper} the instance.
	 */
	public static DeserializationFuncMapper getInstance() {
		if (instance == null)
			instance = new DeserializationFuncMapper();
		
		return instance;
	}
	/**
	 * Singleton class private constructor
	 */
	private DeserializationFuncMapper() {
		funcMap.put(SerializationDataType.TYPE_NULL, new DeserializeNull());
		funcMap.put(SerializationDataType.TYPE_BYTE_ARRAY, new DeserializeByteArray());
		funcMap.put(SerializationDataType.TYPE_INT, new DeserializeInt());
		funcMap.put(SerializationDataType.TYPE_STRING, new DeserializeString());
		funcMap.put(SerializationDataType.TYPE_BOOL, new DeserializeBool());
		funcMap.put(SerializationDataType.TYPE_FLOAT, new DeserializeFloat());
		funcMap.put(SerializationDataType.TYPE_DATETIME, new DeserializeDatetime());
		funcMap.put(SerializationDataType.TYPE_LIST, new DeserializeList());
		funcMap.put(SerializationDataType.TYPE_DICT, new DeserializeDict());
	}
	/**
	 * Get the deserializer with {@link SerializationDataType}.
	 * @param type {@link SerializationDataType}.
	 * @return Function to deserialize the {@link SerializationDataType}.
	 * @throws DeserializerException fails when provided type is not supported.
	 */
	public DeserializeFunc getDeserializer(int type) throws DeserializerException {
		if (funcMap.containsKey(type)) {
			return funcMap.get(type);
		}
		
		throw new DeserializerException(String.format("Serialization type not found %d", type));
	}	
}
/**
 * Deserializer for null type. See {@link DeserializeFunc}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 *
 */
class DeserializeNull implements DeserializeFunc {
	@Override
	public NTObject execute(byte[] byteArray, DeserializeTracker tracker){                
        return new NTNull();
    }
}
/**
 * Deserializer for byte array type. See {@link DeserializeFunc}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 *
 */
class DeserializeByteArray implements DeserializeFunc {
	@Override
	public NTObject execute(byte[] byteArray, DeserializeTracker tracker) throws DeserializerException {
		DeserializationFuncMapper wrapper = DeserializationFuncMapper.getInstance();
		DeserializeFunc intFunc = wrapper.getDeserializer(SerializationDataType.TYPE_INT);
		
        NTInt byteLenType = (NTInt) intFunc.execute(byteArray, tracker);
        int byteLen = byteLenType.getInt();
        byte[] result = java.util.Arrays.copyOfRange(byteArray, tracker.idx, tracker.idx + byteLen);
        tracker.idx += byteLen;
        
        return new NTByteArray(result);
    }
}
/**
 * Deserializer for int type. See {@link DeserializeFunc}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 *
 */
class DeserializeInt implements DeserializeFunc {
	@Override
	public NTObject execute(byte[] byteArray, DeserializeTracker tracker) throws DeserializerException {
        NTObject data = null;
        int bitLength = byteArray[tracker.idx];
        byte[] bitArray = java.util.Arrays.copyOfRange(byteArray, tracker.idx + 1, tracker.idx + bitLength + 1);
        if (bitLength <= 2){
            data = new NTInt(deserializeShort(bitArray));
        } else if (bitLength <= 4){
            data = new NTInt(deserializeInt(bitArray));
        } else if (bitLength <= 8){
            data = new NTInt(deserializeLong(bitArray));
        }
        tracker.idx += bitLength + 1;
        return data;
    }
	/**
	 * Pad the array in the front so that it fits the byte format of a byte length.
	 * @param input the input byte array.
	 * @param byteLength the final length of the padded array.
	 * @return the padded array.
	 */
	private byte[] getPaddedArray(byte[] input, int byteLength) {
		byte[] paddedArr = new byte[byteLength];
		int lenDiff = byteLength - input.length;
		for (int i = paddedArr.length - input.length; i < paddedArr.length; i++) {
			paddedArr[i] = input[i - lenDiff];
		}
		
		return paddedArr;
		
	}
	/**
	 * Deserialize byte array into short.
	 * @param byteArray input byte array.
	 * @return deserialized short.
	 */
	private short deserializeShort(byte[] byteArray){
        byte[] paddedArr = getPaddedArray(byteArray, 2);        
        ByteBuffer wrapped = ByteBuffer.wrap(paddedArr);
        return wrapped.getShort();
    }
	/**
	 * Deserialize byte array into integer.
	 * @param byteArray input byte array.
	 * @return deserialized integer.
	 */
    private int deserializeInt(byte[] byteArray){
    	byte[] paddedArr = getPaddedArray(byteArray, 4);        
        ByteBuffer wrapped = ByteBuffer.wrap(paddedArr);
        return wrapped.getInt();
    }
    /**
	 * Deserialize byte array into long.
	 * @param byteArray input byte array.
	 * @return deserialized long.
	 */
    private long deserializeLong(byte[] byteArray){
    	byte[] paddedArr = getPaddedArray(byteArray, 8);      
        ByteBuffer wrapped = ByteBuffer.wrap(paddedArr);
        return wrapped.getLong();
    }
}
/**
 * Deserializer for String type. See {@link DeserializeFunc}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 *
 */
class DeserializeString implements DeserializeFunc {
	@Override
	public NTObject execute(byte[] byteArray, DeserializeTracker tracker) throws DeserializerException {
		DeserializationFuncMapper wrapper = DeserializationFuncMapper.getInstance();
		DeserializeFunc intFunc = wrapper.getDeserializer(SerializationDataType.TYPE_INT);
		
        NTInt strLenType = (NTInt) intFunc.execute(byteArray, tracker);
        int strLen = strLenType.getInt();
        byte[] bitArray = java.util.Arrays.copyOfRange(byteArray, tracker.idx, tracker.idx + strLen);
        tracker.idx += strLen;
        
        return new NTString(new String(bitArray, StandardCharsets.UTF_8));
    }
}
/**
 * Deserializer for Bool type. See {@link DeserializeFunc}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 *
 */
class DeserializeBool implements DeserializeFunc {
	@Override
	public NTObject execute(byte[] byteArray, DeserializeTracker tracker){
        int result = byteArray[tracker.idx];
        tracker.idx += 1;
        return new NTBool(result == 1);
    }
}
/**
 * Deserializer for Float type. See {@link DeserializeFunc}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 *
 */
class DeserializeFloat implements DeserializeFunc {
	@Override
	public NTObject execute(byte[] byteArray, DeserializeTracker tracker) throws DeserializerException {
		DeserializationFuncMapper wrapper = DeserializationFuncMapper.getInstance();
		DeserializeFunc stringFunc = wrapper.getDeserializer(SerializationDataType.TYPE_STRING);
		
        NTString str = (NTString) stringFunc.execute(byteArray, tracker);
        return new NTFloat(Double.parseDouble(str.get()));
    }
}
/**
 * Deserializer for Date type. See {@link DeserializeFunc}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 *
 */
class DeserializeDatetime implements DeserializeFunc {
	@Override
    public NTObject execute(byte[] byteArray, DeserializeTracker tracker) throws DeserializerException {
		DeserializationFuncMapper wrapper = DeserializationFuncMapper.getInstance();
		DeserializeFunc intFunc = wrapper.getDeserializer(SerializationDataType.TYPE_INT);
		
        NTInt datetimeType = (NTInt) intFunc.execute(byteArray, tracker);
        long timestamp = datetimeType.getLong();
        return new NTDate(new Date(timestamp));
    }
}
/**
 * Deserializer for List type. See {@link DeserializeFunc}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 *
 */
class DeserializeList implements DeserializeFunc {
	@Override
    public NTObject execute(byte[] byteArray, DeserializeTracker tracker) throws DeserializerException {
		DeserializationFuncMapper wrapper = DeserializationFuncMapper.getInstance();
		DeserializeFunc intFunc = wrapper.getDeserializer(SerializationDataType.TYPE_INT);
		
        NTInt strLenType = (NTInt) intFunc.execute(byteArray, tracker);
        int listLength = strLenType.getInt();
        int listType = DeserializeHelper.getType(byteArray, tracker);
        DeserializeFunc func = wrapper.getDeserializer(listType);
        NTObject[] result = new NTObject[listLength];
        for (int i = 0; i < listLength; i++){
            NTObject data = func.execute(byteArray, tracker);
            result[i] = data;
        }
        
        return new NTList(result);
    }
}
/**
 * Deserializer for Dict type. See {@link DeserializeFunc}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 *
 */
class DeserializeDict implements DeserializeFunc {
	@Override
	public NTObject execute(byte[] byteArray, DeserializeTracker tracker) throws DeserializerException {
		DeserializationFuncMapper wrapper = DeserializationFuncMapper.getInstance();
		DeserializeFunc intFunc = wrapper.getDeserializer(SerializationDataType.TYPE_INT);
		
        NTInt strLenType = (NTInt) intFunc.execute(byteArray, tracker);
        int mapLength = strLenType.getInt();                
        LinkedHashMap<String, SimpleEntry<NTObject, NTObject>> result = new LinkedHashMap<String, SimpleEntry<NTObject,NTObject>>();
        for (int i = 0; i < mapLength; i++){
            int keyType = DeserializeHelper.getType(byteArray, tracker);
            DeserializeFunc keyFunc = wrapper.getDeserializer(keyType);
            NTObject keyObj = keyFunc.execute(byteArray, tracker);
            String keyStr = keyObj.toDictKey();
            
            int valType = DeserializeHelper.getType(byteArray, tracker);
            DeserializeFunc valFunc = wrapper.getDeserializer(valType);
            NTObject valObj = valFunc.execute(byteArray, tracker);
            
            SimpleEntry<NTObject, NTObject> entry = new SimpleEntry<NTObject, NTObject>(keyObj, valObj);
            result.put(keyStr, entry);
        }
        
        return new NTDict(result);
    }
}
