import org.sqlite.core.DB;

public class Runner
{
    public static void main(String[] args) {
        try {
            Server s = new Server();
            s.start();
            UI a = new UI();
            UI b = new UI();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}