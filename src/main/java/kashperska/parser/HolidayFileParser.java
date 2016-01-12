package kashperska.parser;

import kashperska.dto.Holiday;
import kashperska.reader.FileReader;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HolidayFileParser {

    private static final String DATE_FORMAT = "yyyy/MM/dd";
    private static final String CYRILLIC_ENCODING = "ISO 8859-5";
    private static Map<String, SimpleDateFormat> formatters = new HashMap<>();

    public Set<Holiday> createHolidays(String filePath) throws IOException {
        Holiday holiday;
        Set<Holiday> holidays = new TreeSet<>();

        FileReader fileReader = new FileReader();
        List<String> lines = fileReader.readFromFile(filePath, CYRILLIC_ENCODING);

        for (String line : lines) {
            String[] splitLine = line.split("(\\d\\s)");

            holiday = createHoliday(splitLine[0], splitLine[1]);

            if(holiday != null){
                holidays.add(holiday);
            }
        }

        return holidays;
    }

    public Date parseDate(String date, String format)throws ParseException{
        SimpleDateFormat formatter = formatters.get(format);

        if(formatter == null){
            formatter = new SimpleDateFormat(format);
            formatters.put(format,formatter);
        }

        return formatter.parse(date);
    }

    private Holiday createHoliday(String date, String name){
        Holiday holiday = null;
        try {
            holiday = new Holiday(parseDate(date, DATE_FORMAT), name);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return holiday;
    }
}
