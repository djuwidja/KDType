package com.djuwidja.networktype.transformer;

import com.djuwidja.networktype.NTDict;

public class NestedTestClass {
	public TestClass innerObj = TestClass.buildSuccessTestObj();

	public NTDict toMQDict() {
		NTDict dict = new NTDict();
		dict.put("innerObj", innerObj.toMQDict());

		return dict;
	}
}
