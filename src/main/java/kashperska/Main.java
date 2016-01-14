package kashperska;

import kashperska.parser.HolidayFileParser;
import kashperska.service.HolidayDisplay;

import java.io.IOException;
import java.text.ParseException;

public class Main {

    private final static String nl = System.getProperty("line.separator");

    public static void main(String[] args) {
        HolidayFileParser parser = new HolidayFileParser();
        HolidayDisplay displayer = null;
        try {
            displayer = new HolidayDisplay(parser.createHolidays("src/main/resources/holidays.txt"));
        } catch (IOException | ParseException e) {
            System.out.println(String.format("Could not parse file due to: %s. Exiting.", e));
            System.exit(1);
        }
        if (displayer != null) {
            System.out.println("Today:" + nl + displayer.showTodayHolidays());
            System.out.println("Tomorrow:" + nl + displayer.showTomorrowsHolidays());
            System.out.println("In next 5 days:" + nl + displayer.showNearFutureHolidays(5, false));
        }

    }
}
