package com.djuwidja.networktype;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.djuwidja.networktype.NTBool;
import com.djuwidja.networktype.NTByteArray;
import com.djuwidja.networktype.NTDate;
import com.djuwidja.networktype.NTDict;
import com.djuwidja.networktype.NTFloat;
import com.djuwidja.networktype.NTInt;
import com.djuwidja.networktype.NTList;
import com.djuwidja.networktype.NTNull;
import com.djuwidja.networktype.NTString;

import java.util.Date;

import org.junit.Assert;;

@ExtendWith(SpringExtension.class)
public class TestNetworkTypeEquality {
	
	@Test
	public void testEquality() {
		final String thisStrContent = "Guten Tag! Gestern war Sonntag. Heute ist Montag. Morgen ist Dienstag.";
		final String thatStrContent = "Guten Tag! Gestern war Sonntag. Heute ist Montag. Morgen ist Dienstag.";
		
		String[] thisStrList = new String[7];
		thisStrList[0] = "Sonntag";
		thisStrList[1] = "Montag";
		thisStrList[2] = "Dienstag";
		thisStrList[3] = "Mittwoch";
		thisStrList[4] = "Donnerstag";
		thisStrList[5] = "Freitag";
		thisStrList[6] = "Samstag";
		
		String[] thatStrList = new String[7];
		thatStrList[0] = "Sonntag";
		thatStrList[1] = "Montag";
		thatStrList[2] = "Dienstag";
		thatStrList[3] = "Mittwoch";
		thatStrList[4] = "Donnerstag";
		thatStrList[5] = "Freitag";
		thatStrList[6] = "Samstag";
		
		NTBool thisBool = new NTBool(true);
		NTBool thatBool = new NTBool(true);		
		Assert.assertEquals(true, thisBool.equals(thatBool));		
		
		NTString thisStr = new NTString(thisStrContent);
		NTString thatStr = new NTString(thatStrContent);		
		Assert.assertEquals(true, thisStr.equals(thatStr));
		
		NTByteArray thisByteArray = new NTByteArray(thisStrContent.getBytes());
		NTByteArray thatByteArray = new NTByteArray(thatStrContent.getBytes());		
		Assert.assertEquals(true, thisByteArray.equals(thatByteArray));
		
		NTDate thisDate = new NTDate(new Date(1575129600000L));
		NTDate thatDate = new NTDate(new Date(1575129600000L));		
		Assert.assertEquals(true, thisDate.equals(thatDate));
		
		NTFloat thisFloat = new NTFloat(3.1415f);
		NTFloat thatFloat = new NTFloat(3.1415f);		
		Assert.assertEquals(true, thisFloat.equals(thatFloat));
		
		NTInt thisInt = new NTInt(46709394);
		NTInt thatInt = new NTInt(46709394);		
		Assert.assertEquals(true, thisInt.equals(thatInt));
		
		NTNull thisNull = new NTNull();
		NTNull thatNull = new NTNull();
		Assert.assertEquals(true, thisNull.equals(thatNull));
		
		NTList thisList = new NTList(thisStrList);
		NTList thatList = new NTList(thatStrList);
		Assert.assertEquals(true, thisList.equals(thatList));
		
		NTDict thisDict = new NTDict();
		NTDict thatDict = new NTDict();
		for (int i = 0; i < thisStrList.length; i++) {
			thisDict.put(thisStrList[i], i);
			thatDict.put(thatStrList[i], i);
		}
		Assert.assertEquals(true, thisDict.equals(thatDict));
	}
	
	@Test
	public void testInequality() {
		final String thisStrContent = "Guten Tag! Gestern war Sonntag. Heute ist Montag. Morgen ist Dienstag.";
		final String thatStrContent = "Guten Abend! Ist fange mein Arbeit am morgenfrüh um 9 Uhr an.";
		
		String[] thisStrList = new String[7];
		thisStrList[0] = "Sonntag";
		thisStrList[1] = "Montag";
		thisStrList[2] = "Dienstag";
		thisStrList[3] = "Mittwoch";
		thisStrList[4] = "Donnerstag";
		thisStrList[5] = "Freitag";
		thisStrList[6] = "Samstag";
		
		String[] thatStrList = new String[7];
		thatStrList[0] = "null";
		thatStrList[1] = "eins";
		thatStrList[2] = "zwei";
		thatStrList[3] = "drei";
		thatStrList[4] = "vier";
		thatStrList[5] = "fünf";
		thatStrList[6] = "sechs";
		
		NTBool thisBool = new NTBool(true);
		NTBool thatBool = new NTBool(false);		
		Assert.assertEquals(false, thisBool.equals(thatBool));		
		
		NTString thisStr = new NTString(thisStrContent);
		NTString thatStr = new NTString(thatStrContent);		
		Assert.assertEquals(false, thisStr.equals(thatStr));
		
		NTByteArray thisByteArray = new NTByteArray(thisStrContent.getBytes());
		NTByteArray thatByteArray = new NTByteArray(thatStrContent.getBytes());		
		Assert.assertEquals(false, thisByteArray.equals(thatByteArray));
		
		NTDate thisDate = new NTDate(new Date(1575129600000L));
		NTDate thatDate = new NTDate(new Date(1575129600999L));		
		Assert.assertEquals(false, thisDate.equals(thatDate));
		
		NTFloat thisFloat = new NTFloat(3.1415f);
		NTFloat thatFloat = new NTFloat(2.7183f);		
		Assert.assertEquals(false, thisFloat.equals(thatFloat));
		
		NTInt thisInt = new NTInt(46709394);
		NTInt thatInt = new NTInt(173173173);		
		Assert.assertEquals(false, thisInt.equals(thatInt));
				
		NTList thisList = new NTList(thisStrList);
		NTList thatList = new NTList(thatStrList);
		Assert.assertEquals(false, thisList.equals(thatList));
		
		NTDict thisDict = new NTDict();
		NTDict thatDict = new NTDict();
		for (int i = 0; i < thisStrList.length; i++) {
			thisDict.put(thisStrList[i], i);
			thatDict.put(thatStrList[i], i);
		}
		Assert.assertEquals(false, thisDict.equals(thatDict));
	}
	
	@Test
	public void testDifferentTypes() {
		final String thisStrContent = "Guten Tag! Gestern war Sonntag. Heute ist Montag. Morgen ist Dienstag.";
		final String thatStrContent = "Guten Tag! Gestern war Sonntag. Heute ist Montag. Morgen ist Dienstag.";
				
		NTBool thisBool = new NTBool(true);
		NTBool thatBool = new NTBool(true);		
		
		NTString thisStr = new NTString(thisStrContent);
		NTString thatStr = new NTString(thatStrContent);		
		
		
		NTFloat thisFloat = new NTFloat(3.1415f);
		NTFloat thatFloat = new NTFloat(3.1415f);		
		
		Assert.assertEquals(false,  thisBool.equals(thisStr));
		Assert.assertEquals(false,  thisBool.equals(thisFloat));
		Assert.assertEquals(false,  thisBool.equals(thatStr));
		Assert.assertEquals(false,  thisBool.equals(thatFloat));
		Assert.assertEquals(false,  thatBool.equals(thisStr));
		Assert.assertEquals(false,  thatBool.equals(thisFloat));
		Assert.assertEquals(false,  thatBool.equals(thatStr));
		Assert.assertEquals(false,  thatBool.equals(thatFloat));
		Assert.assertEquals(false,  thisStr.equals(thisFloat));
		Assert.assertEquals(false,  thisStr.equals(thatFloat));
	}
}
