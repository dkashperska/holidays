package kashperska.service;

import kashperska.parser.DateParser;
import kashperska.util.DateUtil;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public class HolidayDisplay {

    private static final String DATE_FORMAT = "EEE, dd MMM";
    private String nl = System.getProperty("line.separator");

    public String showTodayHolidays(Map<Date, Set<String>> holidays){
        return outputHolidaysNames(holidays.get(DateUtil.getTodayDate()));
    }

    public String showTomorrowsHolidays(Map<Date, Set<String>> holidays){
        return outputHolidaysNames(holidays.get(DateUtil.getDateInDays(1)));
    }

    public String showNearFutureHolidays(Map<Date, Set<String>> holidays, int days, boolean inclusiveToday){
        StringBuilder result = new StringBuilder();

        if(inclusiveToday){
            result.append(getDate(DateUtil.getTodayDate())).append(showTodayHolidays(holidays));
        }

        for(int i = 1; i <= days; i++){
            Date date = DateUtil.getDateInDays(i);
            result.append(getDate(date)).append(outputHolidaysNames(holidays.get(date)));
        }
        return result.toString();
    }

    public String outputHolidaysNames(Set<String> holidaysNames){
        StringBuilder result = new StringBuilder();

        if(holidaysNames != null && !holidaysNames.isEmpty()){
            for(String name : holidaysNames){
                result.append(name).append(nl);
            }
        }

        return result.toString();
    }

    private String getDate(Date date){
        DateParser dateParser = new DateParser();
        return dateParser.convertDate(date, DATE_FORMAT)+ " - ";
    }
}
