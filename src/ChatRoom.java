import java.util.ArrayList;

public class ChatRoom
{
    private Server server;
    private ArrayList<ClientConnection> ccList;

    public ChatRoom(Server s)
    {
        this.server = s;
        this.ccList = new ArrayList<>();
    }

    public void connect(ClientConnection cc)
    {
        this.ccList.add(cc);
    }

    public void disconnect(ClientConnection cc)
    {
        this.ccList.remove(cc);
    }

    public void broadcast(String s)
    {
        for(ClientConnection cc : ccList)
        {
            cc.broadcast(s);
        }
    }
}
