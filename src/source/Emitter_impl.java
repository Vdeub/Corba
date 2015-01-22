package source;
import java.util.ArrayList;
import java.util.List;

public class Emitter_impl extends EmitterPOA
{

	//private List<String> clientsList = new ArrayList<String>();
    private List<Message> messagesList = new ArrayList<Message>();
	
	public List<Message> getMessagesList() {
		return messagesList;
	}

	public void setMessagesList(List<Message> messagesList) {
		this.messagesList = messagesList;
	}

	private String pseudo;
	

 
public String getPseudo() {
		return pseudo;
	}

public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

public  void sendMessage(String to, String message){
//	 Connection_impl.getMyHashMay()
	 Message mes = new Message(pseudo,to,message);
	 messagesList.add(mes);
	 try{
		 Connection_impl.getMyHashMay().get(to).receive(pseudo, message);}
	 catch(Exception E){
		 ;
	 }
 }
	

}
