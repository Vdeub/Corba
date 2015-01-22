package source;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;

public class Server {
	
	private String[] clientList;

	public static void main(String args[]) {

		java.util.Properties props = System.getProperties();

		int status = 0;

		ORB orb = null;

		try {

			orb = ORB.init(args, props);
			run(orb);
		} catch (Exception ex) {
			ex.printStackTrace();
			status = 1;
		}

		if (orb != null) {
			try {
				orb.destroy();
			} catch (Exception ex) {
				ex.printStackTrace();
				status = 1;
				
			}
		}

		System.exit(status);
	}

	static int run(ORB orb) throws Exception {
		org.omg.CORBA.Object obj;
		org.omg.PortableServer.POA rootPOA = org.omg.PortableServer.POAHelper
				.narrow(orb.resolve_initial_references("RootPOA"));

		org.omg.PortableServer.POAManager manager = rootPOA.the_POAManager();

		Connection_impl connectionImpl = new Connection_impl();
		Connection connection = connectionImpl._this(orb);
		
		//Emitter_impl emitterImpl = new Emitter_impl();
		//Emitter emitter = emitterImpl._this(orb);

		obj = orb.resolve_initial_references("NameService");

		NamingContext ctx = NamingContextHelper.narrow(obj);

		if (ctx == null) {
			System.out
					.println("Le composant NameService n'est pas un repertoire");
			return 0;
		}

		NameComponent[] name = new NameComponent[1];

		
		name[0] = new NameComponent("Connection", "");
		//name[1] = new NameComponent("Emitter", "");

		ctx.rebind(name, connection);
		

		name[0] = new NameComponent("Emitter", "");
		//ctx.rebind(name, emitter);
		//ctx.rebind(name, emitter);

		/*
		 * String ref = orb.object_to_string(connection); String refFile =
		 * "Connection.ref"; java.io.PrintWriter out = new java.io.PrintWriter( new
		 * java.io.FileOutputStream(refFile)); out.println(ref); out.close();
		 */

		System.out.println("Liste de clients connectes");

		manager.activate();
		orb.run();

		return 0;
	}

	public String[] getClientList() {
		return clientList;
	}

	public void setClientList(String[] clientList) {
		this.clientList = clientList;
	}
	
}
