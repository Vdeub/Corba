/**
 * 
 */
package test;


import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import source.Emitter_impl;


@SuppressWarnings("deprecation")
public class Emitter_impltest extends TestCase{
	
	private Emitter_impl test ;
	String to;
	String message;
	String pseudo;
	
	public Emitter_impltest(String method) {
	    super(method);
	  }

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		test = new Emitter_impl();
		to = "test";
		message="this is the test for test";
		pseudo= "test_bis";
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test de getPseudo()}.
	 */
	@Test
	public void testGetPseudo() {
		test.setPseudo("test");
		Assert.assertEquals(test.getPseudo(), "test");
	}

	/**
	 * Test de setPseudo(String s)}.
	 */
	@Test
	public void testSetPseudo() {
		test.setPseudo("test");
		Assert.assertEquals(test.getPseudo(), "test");
	}

	/**
	 * Test de sendMessage(String s, String s)}.
	 */
	@Test
	public void testSendMessage()  {
		test.setPseudo(pseudo);
		test.sendMessage(to, message);
		Assert.assertEquals(test.getMessagesList().get(test.getMessagesList().size()-1).getTo(), to);
		Assert.assertEquals(test.getMessagesList().get(test.getMessagesList().size()-1).getMessage(), message);
		Assert.assertEquals(test.getMessagesList().get(test.getMessagesList().size()-1).getFrom(), pseudo);
		
	}

}
