public class Runner 
{
    public static void main(String[] args) {
        //MainUI a = new MainUI();

        Client c = new Client();
        Server s = new Server();

        try {
            s.start();
            UI a = new UI(c);
            //c.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}