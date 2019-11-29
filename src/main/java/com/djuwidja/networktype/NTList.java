/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype;

import java.util.Date;
/**
 * NTString represents list and set interface.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class NTList extends NTObject {
    private NTObject[] value;
    
    public NTList(NTObject[] list){
        super(NTObject.TYPE_LIST);
        value = list;
    }
        
    public NTList(boolean[] list){
        super(NTObject.TYPE_LIST);
        value = new NTBool[list.length];
        for (int i = 0, size = list.length; i < size; i++){
            value[i] = new NTBool(list[i]);
        }
    }
        
    public NTList(byte[][] list){
        super(NTObject.TYPE_LIST);
        value = new NTByteArray[list.length];
        for (int i = 0, size = list.length; i < size; i++){
            value[i] = new NTByteArray(list[i]);
        }
    }
    
    public NTList(Date[] list){
        super(NTObject.TYPE_LIST);
        value = new NTDate[list.length];
        for (int i = 0, size = list.length; i < size; i++){
            value[i] = new NTDate(list[i]);
        }
    }
       
    public NTList(float[] list){
        super(NTObject.TYPE_LIST);
        value = new NTFloat[list.length];
        for (int i = 0, size = list.length; i < size; i++){
            value[i] = new NTFloat(list[i]);
        }
    }
    
    public NTList(double[] list){
        super(NTObject.TYPE_LIST);
        value = new NTFloat[list.length];
        for (int i = 0, size = list.length; i < size; i++){
            value[i] = new NTFloat(list[i]);
        }
    }
    
    public NTList(short[] list){
        super(NTObject.TYPE_LIST);
        value = new NTInt[list.length];
        for (int i = 0, size = list.length; i < size; i++){
            value[i] = new NTInt(list[i]);
        }
    }
    
    public NTList(int[] list){
        super(NTObject.TYPE_LIST);
        value = new NTInt[list.length];
        for (int i = 0, size = list.length; i < size; i++){
            value[i] = new NTInt(list[i]);
        }
    }
    
    public NTList(long[] list){
        super(NTObject.TYPE_LIST);
        value = new NTInt[list.length];
        for (int i = 0, size = list.length; i < size; i++){
            value[i] = new NTInt(list[i]);
        }
    }
    
    public NTList(String[] list){
        super(NTObject.TYPE_LIST);
        value = new NTString[list.length];
        for (int i = 0, size = list.length; i < size; i++){
            value[i] = new NTString(list[i]);
        }
    }
    
    public NTObject[] get(){
        return value;
    }
       
    public boolean[] getBoolList(){
        boolean[] result = new boolean[value.length];
        for (int i = 0; i < value.length; i++){
            result[i] = ((NTBool) value[i]).get();
        }
        return result;
    }
    
    public byte[][] getBytesList(){
        byte[][] result = new byte[value.length][];
        for (int i = 0; i < value.length; i++){
            result[i] = ((NTByteArray) value[i]).get();
        }
        return result;
    }
    
    public Date[] getDateList(){
        Date[] result = new Date[value.length];
        for (int i = 0; i < value.length; i++){
            result[i] = ((NTDate) value[i]).get();
        }
        return result;
    }
    
    public NTDict[] getMQDictList(){
        NTDict[] result = new NTDict[value.length];
        for (int i = 0; i < value.length; i++){
            result[i] = (NTDict) value[i];
        }
        return result;
    }
    
    public float[] getFloatList(){
        float[] result = new float[value.length];
        for (int i = 0; i < value.length; i++){
            result[i] = ((NTFloat) value[i]).getFloat();
        }
        return result;
    }
    
    public double[] getDoubleList(){
        double[] result = new double[value.length];
        for (int i = 0; i < value.length; i++){
            result[i] = ((NTFloat) value[i]).getDouble();
        }
        return result;
    }
    
    public short[] getShortList(){
        short[] result = new short[value.length];
        for (int i = 0; i < value.length; i++){
            result[i] = ((NTInt) value[i]).getShort();
        }
        return result;
    }
    
    public int[] getIntList(){
        int[] result = new int[value.length];
        for (int i = 0; i < value.length; i++){
            result[i] = ((NTInt) value[i]).getInt();
        }
        return result;
    }
    
    public long[] getLongList(){
        long[] result = new long[value.length];
        for (int i = 0; i < value.length; i++){
            result[i] = ((NTInt) value[i]).getLong();
        }
        return result;
    }
    
    public NTList[] getMQListList(){
        NTList[] result = new NTList[value.length];
        for (int i = 0; i < value.length; i++){
            result[i] = (NTList) value[i];
        }
        return result;
    }
    
    public String[] getStrList(){
        String[] result = new String[value.length];
        for (int i = 0; i < value.length; i++){
            result[i] = ((NTString) value[i]).get();
        }
        return result;
    }
    
    public void set(NTObject[] v){
        value = v;
    }
    
    @Override
    public String toDictKey(){
        return toString();
    }
    
    @Override
    public String toString(){
        String str = "";
        for (int i = 0, size = value.length; i < size; i++){
            if (i != 0){
                str += ", ";
            }
            NTObject t = value[i];
            str += t.toString();
        }
        
        return String.format("[%s]", str);
    }
    
    @Override 
    public String toJsonString(){
        String str = "";
        for (int i = 0, size = value.length; i < size; i++){
            if (i != 0){
                str += ", ";
            }
            NTObject t = value[i];
            str += t.toJsonString();
        }
        
        return String.format("[%s]", str);
    }
}
