package kashperska.service;

import kashperska.dto.Holiday;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class HolidayDisplay {

    public String showTodayHolidays(Set<Holiday> holidays){
        Date today = new Date();
        Iterator<Holiday> it = holidays.iterator();
        while (it.hasNext()){
            Holiday holiday = it.next();
            if(holiday.getDate().equals(today))
                return holiday.toString();
        }
        return null;
    }
}
