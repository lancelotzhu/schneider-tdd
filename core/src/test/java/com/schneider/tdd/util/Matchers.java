package com.schneider.tdd.util;

import org.easymock.EasyMock;
import org.easymock.IArgumentMatcher;

public class Matchers {
	
	private Matchers() {		
	}
	
	public static <T> T eq(Class<T> clazz, IArgumentMatcher matcher) {
		EasyMock.reportMatcher(matcher);
	    return null;
	}

}
