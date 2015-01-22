/**
 * 
 */
package test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


//import source.Message;
import source.Receiver_impl;


@SuppressWarnings("deprecation")
public class Receiver_impltest extends TestCase{
	private String[] clients= new String[5];
	private List<String> lesClientsTest ;
	private Receiver_impl test ;
	
	
	public Receiver_impltest(String method) {
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
		test = new Receiver_impl();
		lesClientsTest= new ArrayList<String>() ;
		clients[0] ="client01";
		clients[1] ="client02";
		for(int i =0; i< clients.length;i++){
			lesClientsTest.add(clients[i]);
		}
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link source.Receiver_impl#getClientsList()}.
	 */
	@Test
	public void testGetClientsList() {
		test.addClient("test");
		List<String> clientsListget = new ArrayList<String>();
		clientsListget.add("test");
		Assert.assertEquals(test.getClientsList(),clientsListget);
		
		
	}

	/**
	 * Test method for {@link source.Receiver_impl#receive(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testReceive() {
		//String from="client01";
		//String message="Ceci est un test de reception de message";
		//Assert.assertNull(test.receive(from, message));
		
	}

	/**
	 * Test method for {@link source.Receiver_impl#initClients(java.lang.String[])}.
	 */
	@Test
	public void testInitClients() {
		
		 test.initClients(clients);
		
		 Assert.assertEquals(test.getClientsList(), lesClientsTest);
		
	}

	/**
	 * Test de addClient(String s)}.
	 */
	@Test
	public void testAddClient() {
		String newClient = "client03";
		lesClientsTest.add(newClient);
		test.addClient(newClient);
		List<String> lesClientstest=test.getClientsList();
		String getClient = lesClientstest.get(lesClientstest.size()-1);
		
		Assert.assertEquals(getClient, newClient);
		
	}

	/**
	 * Test de remClient(String s)}.
	 */
	@Test
	public void testRemClient() {
		test.addClient("client01");
		List<String> lesClientsTestbegin=test.getClientsList();
		String removedClient=lesClientsTestbegin.get(lesClientsTestbegin.size()-1);
		test.remClient(removedClient);
		List<String> lesClientsTestend=test.getClientsList();
		lesClientsTestbegin.remove(removedClient);
		Assert.assertEquals(lesClientsTestend,lesClientsTestbegin);
		
	}
	 public static junit.framework.Test suite() {
	        System.out.println("create suite");
	        TestSuite suite = new TestSuite();
	        suite.addTest(new Receiver_impltest("testInitClients"));
	        suite.addTest(new Receiver_impltest("testAddClient"));
	        suite.addTest(new Receiver_impltest("testRemClient"));
	        return suite;
	    }


}
