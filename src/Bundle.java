import java.io.Serializable;

public class Bundle implements Serializable {
    private int action;
    private String[] payload;
    private String msg;
    private int roomNumber;

    public Bundle() {
        this.action = 0;
    }

    public Bundle(int a, String[] pl) {
        this.action = a;
        this.payload = pl;
    }

    public Bundle(int rn) {
        this.action = 0;
        this.roomNumber = rn;
    }

    public Bundle(String str) {
        this.action = 1;
        this.msg = str;
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

    public int getRoomNumber() { return roomNumber; }

    public void setRoomNumber(int rn) { this.roomNumber = rn; }

    public String getMsg() { return msg; }

    public void setMsg(String str) { this.msg = str; }
}
