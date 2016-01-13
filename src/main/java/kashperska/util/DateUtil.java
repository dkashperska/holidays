package kashperska.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

    public static Date getTodayDate(){
        return convertDate(getTodayDateInMidnight());
    }

    public static Date getDateInDays(int days){
        return convertDate(getTodayDateInMidnight().plusDays(days));
    }

    public static LocalDateTime getTodayDateInMidnight(){
        return LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    public static Date convertDate(LocalDateTime ldt){
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
}
