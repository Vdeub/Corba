package source;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

public class Connection_impl extends ConnectionPOA {

	// private List<String> clientsList = new ArrayList<String>();
	private static List<Emitter_impl> emitterList = new ArrayList<Emitter_impl>();
	
	
	public static List<Emitter_impl> getEmitterList() {
		return emitterList;
	}

	@SuppressWarnings("static-access")
	public void setEmitterList(List<Emitter_impl> emitterList) {
		this.emitterList = emitterList;
	}

	private static HashMap<String, Receiver> myHashMap = new HashMap<String, Receiver>();

	// private Message mes;
	// private List<Message> messagesList = new ArrayList<Message>();

	public Emitter connect(String pseudo, Receiver rcv) {
		// clientsList.add(pseudo);
		Emitter_impl emit = new Emitter_impl();
		emit.setPseudo(pseudo);
		Emitter emitter = null;
		try {
			//System.out.println("This is the beginning");
			emitter = EmitterHelper.narrow(_default_POA().servant_to_reference(
					emit));
			myHashMap.put(pseudo, rcv);
		} catch (ServantNotActive e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongPolicy e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] clientList;
		clientList = new String[emitterList.size()];
		for(int i = 0;i< clientList.length;i++){
			clientList[i]=emitterList.get(i).getPseudo();
			System.out.println(clientList[i]);
		}
		rcv.initClients(clientList);
		emitterList.add(emit);
		
		for(int i=0; i<myHashMap.size();i++){
			myHashMap.get(emitterList.get(i).getPseudo()).addClient(pseudo);
		}
		return emitter;
	}

	public void disconnect(String pseudo) {
		// clientsList.remove(pseudo);
		byte[] id;
		for (int i = 0; i < emitterList.size(); i++)
			if (emitterList.get(i).getPseudo().equals(pseudo))
				try {
					id = _default_POA().servant_to_id(	emitterList.get(i));
					_default_POA().deactivate_object(id);
					myHashMap.remove(emitterList.get(i).getPseudo());
					emitterList.remove(i);

				} catch (ServantNotActive e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WrongPolicy e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ObjectNotActive e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		

	}

	public static HashMap<String, Receiver> getMyHashMay() {
		return myHashMap;
	}

}
