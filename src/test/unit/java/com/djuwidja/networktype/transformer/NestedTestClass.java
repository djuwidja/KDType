package com.djuwidja.networktype.transformer;

import com.djuwidja.networktype.NTDict;

public class NestedTestClass {
	public TestClass innerObj = TestClass.buildSuccessTestObj();

	public NTDict toNTDict() {
		NTDict dict = new NTDict();
		dict.put("innerObj", innerObj.toNTDict());

		return dict;
	}
}
