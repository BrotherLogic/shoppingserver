package com.brotherlogic.shopping;

import org.junit.After;
import org.junit.Before;

import com.brotherlogic.shopping.web.ContextListener;

/**
 * Unit test for simple App.
 */
public class TestBase {
	private ContextListener listener;

	@Before
	public void tearup() {
		listener = new ContextListener();
		listener.contextInitialized(null);
	}

	@After
	public void teardown() {
		listener.contextDestroyed(null);
	}

	String responseString = "";

}
