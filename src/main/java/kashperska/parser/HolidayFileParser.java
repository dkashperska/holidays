package kashperska.parser;

import kashperska.reader.FileReader;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.*;

public class HolidayFileParser {

    private static final String DATE_FORMAT = "yyyy/MM/dd";
    private static final String WHITESPACE = " ";

    public Map<Date, Set<String>> createHolidays(String filePath) throws IOException, ParseException {
        Map<Date, Set<String>> holidays = new TreeMap<>();

        FileReader fileReader = new FileReader();
        List<String> lines = fileReader.readFromFile(filePath, StandardCharsets.UTF_8);

        for (String line : lines) {
            String[] splitLine = splitLine(line);

            Date date = new DateParser().parseDate(splitLine[0], DATE_FORMAT);

            if (date != null) {
                if (holidays.containsKey(date)) {
                    holidays.get(date).add(splitLine[1]);
                } else {
                    Set<String> names = new LinkedHashSet<>();
                    names.add(splitLine[1]);
                    holidays.put(date, names);
                }
            }
        }

        return holidays;
    }

    public String[] splitLine(String line) {
        String[] result = new String[2];
        result[0] = StringUtils.substringBefore(line, WHITESPACE);
        result[1] = StringUtils.substringAfter(line, WHITESPACE);
        return result;
    }


}
