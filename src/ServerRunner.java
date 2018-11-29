public class ServerRunner
{
    public static void main(String[] args)
    {
        try
        {
            Server s = new Server();
            s.start();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}