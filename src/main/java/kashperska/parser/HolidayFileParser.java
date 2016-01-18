package kashperska.parser;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.*;

public class HolidayFileParser {

    private static final String WHITESPACE = " ";

    /**
     * Returns map of holidays after parsing input lines.
     * Lines should be in format: “Date Holiday Name”.
     * Each new holiday must start with a new line.
     * Everything between the first space and the new line character is considered the name of the holiday.
     * Throws ParseException when date has invalid format.
     * @param lines collection of lines, if null throws IllegalArgumentException
     * @param dateFormat format of holiday date
     * @return Map with date keys and set of holiday name values
     * @throws ParseException, IllegalArgumentException
     */
    public Map<Date, Set<String>> createHolidays(Collection<String> lines, String dateFormat) throws ParseException {
        Map<Date, Set<String>> holidays = new TreeMap<>();

        if(lines == null){
            throw new IllegalArgumentException("Lines to parse should not be null");
        }

        for (String line : lines) {
            String[] splitLine = splitLine(line);

            Date date = new DateParser().parseDate(splitLine[0], dateFormat);

            if (holidays.containsKey(date)) {
                holidays.get(date).add(splitLine[1]);
            } else {
                Set<String> names = new LinkedHashSet<>();
                names.add(splitLine[1]);
                holidays.put(date, names);
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
