public class Bundle {
    private int action;
    private String[] payload;

    public Bundle() {
        this.action = 0;
        this.payload = null;
    }

    public Bundle(int a, String[] pl) {
        this.action = a;
        this.payload = pl;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String[] getPayload() {
        return payload;
    }

    public void setPayload(String[] payload) {
        this.payload = payload;
    }
}
