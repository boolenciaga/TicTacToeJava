package modules;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Game extends BaseModel {
//    private int  gameId;
    private String startTime;
    private String endTime;
    private int  p1Id;
    private int p2Id;
    private int starterId;
    private int winnerId;

    public Game(int p1) {
        startTime = "";
        endTime   = "";
        setP1Id(p1);
        setStarterId(p1);

    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime() {
        Date ended = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        endTime = dateFormat.format(ended);
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime() {
        Date started = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        startTime = dateFormat.format(started);
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
