public class Runner 
{
    public static void main(String[] args) {
        DBConnenction test = new DBConnenction();
       
        test.connect();
        test.createTable();
        
        UI a = new UI();
        
    }
}