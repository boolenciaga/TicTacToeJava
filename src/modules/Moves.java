package modules;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Moves  extends BaseModel{
//    private int moveId;
    private int gameId;
    private int playerId;
    private int Xcoord;
    private int Ycoord;


    public String getTime()
    {
        String strDate;

        Date time = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        strDate = dateFormat.format(time);

        return strDate;
    }

}
