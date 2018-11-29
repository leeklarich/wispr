import java.io.*;
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

    public void broadcast(byte[] s)
    {
        logMsg(s.toString());

        for(ClientConnection cc : ccList)
        {
            cc.broadcast(s);
        }
    }

    public void logMsg(String s)
    {
        FileWriter fw;
        BufferedWriter bw;

        try
        {
            fw = new FileWriter("./logs/" + this.server.rooms.indexOf(this) + ".txt", true);
            bw = new BufferedWriter(fw);

            bw.write(s + "\n");
            bw.close();
            fw.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
