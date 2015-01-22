/**
 * 
 */
package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import source.Connection;
import source.Connection_impl;
import source.Emitter;
import source.Emitter_impl;
import source.Receiver;
import source.Receiver_impl;


 
@SuppressWarnings("deprecation")
public class Connection_impltest {
	
	@SuppressWarnings("unused")
	private List<Emitter_impl> emitterList ;
	private HashMap<String, Receiver> myHashMap ;
	private Receiver rcv;
	private String pseudo;
	private String pseudo_bis;
	private Connection_impl test;
	
	@SuppressWarnings("unused")
	private Emitter emitter;
	private Connection connection;
	private HashMap<String, Receiver> myHashMap01 ;
	
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		org.omg.CORBA.ORB orb = null;
		String[] args = new String[0];
		java.util.Properties props = System.getProperties();
		 orb = ORB.init(args, props);
		emitterList = new ArrayList<Emitter_impl>();
		myHashMap = new HashMap<String, Receiver>();
		myHashMap01 = new HashMap<String, Receiver>();
		test = new Connection_impl();
		
		org.omg.CORBA.Object obj = null;
	    org.omg.PortableServer.POA rootPOA = org.omg.PortableServer.POAHelper
				.narrow(orb.resolve_initial_references("RootPOA"));

		org.omg.PortableServer.POAManager manager = rootPOA.the_POAManager();
		Receiver_impl receiverImpl = new Receiver_impl();
		rcv = receiverImpl._this(orb);
		pseudo = "test";
		pseudo_bis = "test_bis";
		obj=orb.resolve_initial_references("NameService");
		NamingContext ctx = NamingContextHelper.narrow(obj);
		NameComponent[] name = new NameComponent[1];
		
		name[0]=new NameComponent("Connection","");
		manager.activate();
		obj = ctx.resolve(name);
		connection = test._this(orb);
		
		 
	}

	
	/**
	 * Test de connect(String s, Receiver r)}.
	 */
	
	@Test
	public void testConnect() {
		emitter = connection.connect(pseudo, rcv);
		
		myHashMap01=Connection_impl.getMyHashMay();
		HashMap<String, Receiver> myHashMap02 = new HashMap<String, Receiver>();
		myHashMap02.put(pseudo, rcv);
		Assert.assertEquals(myHashMap01,myHashMap02);
		List<Emitter_impl> emitterList = new ArrayList<Emitter_impl>();
		emitterList=Connection_impl.getEmitterList();
		Emitter_impl emit = new Emitter_impl();
		emit.setPseudo(pseudo);
		Assert.assertEquals(emitterList.get(emitterList.size()-1).getPseudo(),emit.getPseudo());
		
	
	}

	/**
	 * Test de disconnect(String s)}.
	 */
	
	@Test
	public void testDisconnect() {
		connection.connect(pseudo_bis, rcv);
		emitter = connection.connect(pseudo, rcv);
		//myHashMap01=Connection_impl.getMyHashMay();
		@SuppressWarnings("unused")
		List<Emitter_impl> emitterList = new ArrayList<Emitter_impl>();
		
		connection.disconnect(pseudo);
		
		connection.disconnect(pseudo_bis);
		Assert.assertNull(Connection_impl.getMyHashMay().get(rcv));
		myHashMap = Connection_impl.getMyHashMay();
		//myHashMap01.
		Assert.assertTrue(myHashMap.isEmpty());
		Assert.assertTrue(Connection_impl.getEmitterList().isEmpty());
		
	
	}

	/**
	 * Test de getMyHashMay()}.
	 */
	
	@Test
	public void testGetMyHashMay() {
		connection.connect(pseudo_bis, rcv);
		myHashMap = Connection_impl.getMyHashMay();
		myHashMap01.put(pseudo_bis,rcv);
		Assert.assertEquals(myHashMap01, myHashMap);
	}

	
}
