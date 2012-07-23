package com.schneider.tdd.util;

import org.easymock.EasyMock;
import org.easymock.IArgumentMatcher;

public class Matchers {
	
	private Matchers() {		
	}
	
	public static <T> T eq(BaseMatcher<T> matcher) {
		EasyMock.reportMatcher(matcher.asArgumentMatcher());
	    return null;
	}

	public static abstract class BaseMatcher<T> {

		public abstract boolean matches(T actual);
		
		public abstract void appendTo(StringBuffer buffer);

		public IArgumentMatcher asArgumentMatcher() {
			return new IArgumentMatcher() {
				
				@SuppressWarnings("unchecked")
				public boolean matches(Object argument) {
					return BaseMatcher.this.matches((T)argument);
				}

				public void appendTo(StringBuffer buffer) {
					BaseMatcher.this.appendTo(buffer);
				}
				
			};
		}
	}
}
