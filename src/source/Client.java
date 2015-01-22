package source;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;



public class  Client{

public static void main(String args[])
{
    java.util.Properties props = System.getProperties();
    

    int status = 0;
    org.omg.CORBA.ORB orb = null;

try
    {
    orb = ORB.init(args, props);
    run(orb);
    }
catch(Exception ex)
    {
    ex.printStackTrace();
    status = 1;
    }

if(orb != null)
{
    try
    {
    orb.destroy();
    }
    catch(Exception ex)
    {
    ex.printStackTrace();
    status = 1;
    }
}

System.exit(status);
}

static void run(ORB orb) throws Exception
{
    org.omg.CORBA.Object obj = null;
	//modified
    org.omg.PortableServer.POA rootPOA = org.omg.PortableServer.POAHelper
			.narrow(orb.resolve_initial_references("RootPOA"));

	org.omg.PortableServer.POAManager manager = rootPOA.the_POAManager();
    
    
	Receiver_impl receiverImpl = new Receiver_impl();
	Receiver receiver = receiverImpl._this(orb);
	
	try
	{
	
	obj=orb.resolve_initial_references("NameService");
	}
	catch(InvalidName e)
	{
		e.printStackTrace();
		System.exit(1);
	}
	
	
	NamingContext ctx = NamingContextHelper.narrow(obj);
	
	
	if (ctx==null)
	{
		System.out.println("Le composant NameService n'est pas un repertoire");
		System.exit(1);
	}
	
	NameComponent[] name = new NameComponent[1];
	
	name[0]=new NameComponent("Connection","");
	//ctx.rebind(name, receiver);
	
	//modified
	manager.activate();
	
	try
	{
	//Chercher name dans les repertoire, retourne interface de l'objet
	obj = ctx.resolve(name);
	}
	catch (Exception e)
	{
		System.out.println("Composant inconnu");
		e.printStackTrace();
		System.exit(1);
	}
	
  



	Connection connection = ConnectionHelper.narrow(obj);
	
	
	String pseudo;
	String to;
	String message ;
	String stop ;
    try { 
    	System.out.println("Entrer Votre Pseudo:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        pseudo = br.readLine();     
    	System.out.println("Voici les clients connectes:" );
    	Emitter emitter = connection.connect(pseudo, receiver);

    	boolean bstop=true;
    	while(bstop){
    		System.out.println("Envoyer a qui ? ");
    	to = br.readLine();
    	System.out.println("Message : ");
    	message = br.readLine();
    	emitter.sendMessage(to, message);
    	System.out.println("Arreter ? (Y pour arreter) ");
    	stop = br.readLine();
    	if(stop.equals("y")||stop.equals("Y"))
    		bstop = false;
    	}
    	orb.run();
    } catch (IOException e) {  
        e.printStackTrace();  
    }  
	

	
	
}


}

