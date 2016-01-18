package kashperska;

import kashperska.parser.HolidayFileParser;
import kashperska.reader.FileReader;
import kashperska.service.HolidayDisplay;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.List;

public class Main {

    private final static String nl = System.getProperty("line.separator");

    public static void main(String[] args) {
        HolidayFileParser parser = new HolidayFileParser();
        FileReader fileReader = new FileReader();
        HolidayDisplay displayer = null;
        try {
            List<String> lines = fileReader.readFromFile("src/main/resources/holidays.txt", StandardCharsets.UTF_8);
            displayer = new HolidayDisplay(parser.createHolidays(lines));
        } catch (IOException | ParseException e) {
            System.out.println(String.format("Could not parse file due to: %s. Exiting.", e));
            System.exit(1);
        }
        System.out.println("Today:" + nl + displayer.showTodayHolidays());
        System.out.println("Tomorrow:" + nl + displayer.showTomorrowsHolidays());
        System.out.println("In next 5 days:" + nl + displayer.showNearFutureHolidays(5, false));
    }
}
