package modules;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Game extends BaseModel {
//    private int  gameId;
    private String startTime;
    private String endTime;
    private int  p1Id;
    private int p2Id;
    private int starterId;
    private int winnerId;

    public Game() {

    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime() {
        Date created = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        endTime = dateFormat.format(created);
    }

    public void setStartTime() {
        Date created = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        startTime = dateFormat.format(created);
    }

    public int getP1Id() {
        return p1Id;
    }

    public void setP1Id(int p1Id) {
        this.p1Id = p1Id;
    }

    public int getP2Id() {
        return p2Id;
    }

    public void setP2Id(int p2Id) {
        this.p2Id = p2Id;
    }

    public int getStarterId() {
        return starterId;
    }

    public void setStarterId(int starterId) {
        this.starterId = starterId;
    }

    public int getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }
}
