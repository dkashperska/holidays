package kashperska;

import kashperska.parser.HolidayFileParser;
import kashperska.service.HolidayDisplay;

import java.io.Console;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Main {

    private static String nl = System.getProperty("line.separator");

    public static void main(String [] args){
        HolidayFileParser parser = new HolidayFileParser();
        HolidayDisplay displayer = new HolidayDisplay();
        Map<Date, Set<String>> holidays = new TreeMap<>();
        try {
            holidays = parser.createHolidays("src/main/resources/holidays.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!holidays.isEmpty()){
            System.out.println("Today:" + nl + displayer.showTodayHolidays(holidays));
            System.out.println("Tomorrow:" + nl + displayer.showTomorrowsHolidays(holidays));
            System.out.println("In next 5 days:" + nl + displayer.showNearFutureHolidays(holidays, 5, false));
        }

    }
}
