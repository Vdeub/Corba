package source;
import java.util.ArrayList;
import java.util.List;

public class Receiver_impl extends ReceiverPOA
{

	private List<String> clientsList = new ArrayList<String>();
	private Message mes;
	//private List<Message> messagesList = new ArrayList<Message>();
	
	

	public List<String> getClientsList(){
		return clientsList;
	}


@Override
public void receive(String from, String message) {
	// TODO Auto-generated method stub
	System.out.println(from.toUpperCase()+" : " + message);
	
}

@Override
public void initClients(String[] clients) {
	// TODO Auto-generated method stub
	for(int i =0; i< clients.length;i++){
		clientsList.add(clients[i]);
		System.out.println(clients[i]);
	}
}

@Override
public void addClient(String client) {
	// TODO Auto-generated method stub
	clientsList.add(client);
}

@Override
public void remClient(String client) {
	// TODO Auto-generated method stub
	clientsList.remove(client);
}


public Message getMes() {
	return mes;
}


public void setMes(Message mes) {
	this.mes = mes;
}
}
