package com.djuwidja.datautils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

/**
 * Helper service to transfer all variables with the same name and type from an object to another object.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
@Service
public class TransferDataService {
	/**
	 * Creates a new object using destCls and then attempt to transfer all fields from src to the new object. 
	 * Invoke {@link TransferDataService#transferFields(Object, Object)}.
	 * @param <T> parametric type of destCls.
	 * @param src the source object.
	 * @param destCls the destination class.
	 * @return <T> the newly created object with fields from src transfered into it.
	 * @throws TransferDataServiceException fails when unable to transfer data.
	 */
	public <T> T transferData(Object src, Class<T> destCls) throws TransferDataServiceException {
		T returnData = null;
		
		try {
        	returnData = (T) destCls.getDeclaredConstructor().newInstance();
        	transferFields(src, returnData);
        	return returnData;
		} 
		catch (final Exception e) {
			throw new TransferDataServiceException("Unable to transfer data.", e);
		}
    }
	/**
	 * Attempt to transfer all fields from src object to dest object. 
	 * Invoke {@link TransferDataService#transferFields(Object, Object, String...)}.
	 * @param src the source object.
	 * @param dest the destination object.
	 * @throws TransferDataServiceException fails when unable to transfer data.
	 */
	public void transferFields(Object src, Object dest) throws TransferDataServiceException {
		Class<?> srcCls = src.getClass();
		Field[] fieldList = srcCls.getFields();
				
		transferFields(src, dest, fieldList);
	}
	/**
	 * Attempt to transfer selected fields from src object to dest object.
	 * @param src the source object.
	 * @param dest the destination object.
	 * @param fieldNameList names of field to transfer.
	 * @throws TransferDataServiceException fails when unable to transfer data.
	 */
	public void transferFields(Object src, Object dest, String... fieldNameList) throws TransferDataServiceException {
		Class<?> srcCls = src.getClass();
		Set<Field> srcFieldSet = new HashSet<Field>();
		try {
			for (int i = 0; i < fieldNameList.length; i++) {
				Field srcField = srcCls.getField(fieldNameList[i]);
				srcFieldSet.add(srcField);
			}
		}
		catch (final NoSuchFieldException e) {
			throw new TransferDataServiceException("Unable to transfer fields.", e);
		}
		
		Field[] srcFieldList = srcFieldSet.toArray(new Field[srcFieldSet.size()]);
		transferFields(src, dest, srcFieldList);		
	}
	/**
	 * private function to attempt transfers fields from src to dest. Will first computes transferrable fields 
	 * by invoking {@link TransferDataService#computeTransferableFieldMap(Object, Object, Field...)} and then transfer
	 * the fields from src object to dest object base on the result. 
	 * <p>
	 * Exceptions are caught during the transfer process if the fields are not of the same type. The process will then
	 * skip the field and proceed to the next.
	 * @param src the source object.
	 * @param dest the destination object.
	 * @param srcFieldList names of field to transfer.
	 */
	private void transferFields(Object src, Object dest, Field... srcFieldList) {
		Map<Field, Field> transferableFieldMap = computeTransferableFieldMap(src, dest, srcFieldList);
		
		for (Map.Entry<Field, Field> entry : transferableFieldMap.entrySet()) {
			try {
				Field srcField = entry.getKey();
				Field destField = entry.getValue();
				
				destField.set(dest, srcField.get(src));
			}
			catch (final IllegalAccessException e) {

			}	
		}
	}
	/**
	 * Compute a map of fields of the same name between src object and dest object.
	 * @param src the source object.
	 * @param dest the destination object.
	 * @param srcFieldList names of field to transfer.
	 * @return A map of fields with name shared between src object and dest object.
	 */
	private Map<Field, Field> computeTransferableFieldMap(Object src, Object dest, Field... srcFieldList) {
		Map<Field, Field> transferableFieldMap = new HashMap<>();
	
		for (int i = 0; i < srcFieldList.length; i++) {
			try {
				Class<?> destCls = dest.getClass();
				
				Field srcField = srcFieldList[i];
				Field destField = destCls.getField(srcField.getName());
				
				transferableFieldMap.put(srcField, destField);
			}
			catch (final NoSuchFieldException e) {

			}	
		}
		
		return transferableFieldMap;
	}
}
