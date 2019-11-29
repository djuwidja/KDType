/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype;

import java.util.Date;
/**
 * NTString represents Date object.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class NTDate extends NTObject {
    private Date value;
    
    public NTDate(Date v){
        super(NTObject.TYPE_DATETIME);
        value = v;
    }
    
    public Date get(){
        return value;
    }
    
    public void set(Date v){
        value = v;
    }
    
    @Override
    public String toDictKey(){
        return toString();
    }
    
    @Override
    public String toString(){
        return String.format("Date(%s)", value.toString());
    }
    
    @Override
    public String toJsonString(){
        return String.format("%d", value.getTime());
    }
}
