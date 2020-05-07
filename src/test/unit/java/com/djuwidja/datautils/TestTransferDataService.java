package com.djuwidja.datautils;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.Assert;

@ExtendWith(SpringExtension.class)
public class TestTransferDataService {
	private TransferDataService migrateDataService = new TransferDataService();
	
	@Test
	public void testMigrateData() throws TransferDataServiceException {
		TestDataClass testSubject = new TestDataClass();
		testSubject.testBool = true;
		testSubject.testInt = 16938;
		testSubject.testString = "Meine Wohnung habt vier Zimmers. Es ist groß und sehr gemütlich. Es habt ein Abstellzimmer, ein Wohnzimmer mit offene Kücher, ein Schlafzimmer und ein Bad.";
	
		TestDataClass testObject = migrateDataService.transferData(testSubject, TestDataClass.class);
		
		Assert.assertEquals(testSubject.testBool, testObject.testBool);
		Assert.assertEquals(testSubject.testInt, testObject.testInt);
		Assert.assertEquals(testSubject.testString, testObject.testString);
	}
	
	@Test
	public void testMigrateNullFields() throws TransferDataServiceException {
		TestDataClass testSubject = new TestDataClass();
		testSubject.testBool = true;
		testSubject.testInt = 16938;
		testSubject.testString = null;
		
		TestDataClass testObject = migrateDataService.transferData(testSubject, TestDataClass.class);
		
		Assert.assertEquals(testSubject.testBool, testObject.testBool);
		Assert.assertEquals(testSubject.testInt, testObject.testInt);
		Assert.assertEquals(testSubject.testString, testObject.testString);
	}
	
	@Test
	public void testMigrateNull() {
		try {
			migrateDataService.transferData(null, TestDataClass.class);
			Assert.fail("Exception is not thrown");
		} catch (final TransferDataServiceException e) {
			
		}		
	}
	
	@Test
	public void testMigrateTransferField() throws TransferDataServiceException {
		TestDataClass testSubject = new TestDataClass();
		testSubject.testBool = true;
		testSubject.testInt = 16938;
		testSubject.testString = "Meine Wohnung habt fünf Zimmers. Es ist groß und sehr gemütlich. Es habt ein Abstellzimmer, ein Wohnzimmer mit offene Kücher, ein Schlafzimmer, und zwei Bäder.";
		
		TestTransferDataClass testObject = new TestTransferDataClass();
		migrateDataService.transferFields(testSubject, testObject, "testString");

		Assert.assertEquals(testSubject.testString, testObject.testString);
	}
}
