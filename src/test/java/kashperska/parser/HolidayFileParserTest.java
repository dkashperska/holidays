package kashperska.parser;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HolidayFileParserTest {

    private static final String FIRST_DATE = "2016/1/13";
    private static final String FIRST_HOLIDAY = "Holiday 1";
    private static final String SECOND_HOLIDAY = "Holiday 2";
    private static final String SECOND_DATE = "2016/1/14";
    private static final String THIRD_HOLIDAY = "Holiday 3";
    private static final String THIRD_DATE = "2016/1/15";
    private static final String FOURTH_HOLIDAY = "Holiday 4";
    private static final String INVALID_DATE = "1.15.2016";
    private static final String FIFTH_HOLIDAY = "Holiday 5";
    private static final String WHITESPACE = " ";
    private static final String DATE_FORMAT = "yyyy/MM/dd";
    private static final String INVALID_DATE_FORMAT = "yyyy.dd.MM";


    private HolidayFileParser holidayFileParser;
    private List<String> lines;

    @Before
    public void setUp() throws IOException {
        holidayFileParser = new HolidayFileParser();
        lines = new ArrayList<>();
        lines.add(FIRST_DATE + WHITESPACE + FIRST_HOLIDAY);
        lines.add(FIRST_DATE + WHITESPACE + SECOND_HOLIDAY);
        lines.add(SECOND_DATE + WHITESPACE + THIRD_HOLIDAY);
        lines.add(THIRD_DATE + WHITESPACE + FOURTH_HOLIDAY);
    }

    @Test
    public void shouldCreateMapOfHolidays() throws ParseException {
        Map<Date, Set<String>> holidays = holidayFileParser.createHolidays(lines,DATE_FORMAT);
        Set<String> firstDateHolidays = holidays.get(parseDate(FIRST_DATE));
        Iterator<String> it1 = firstDateHolidays.iterator();
        assertEquals(FIRST_HOLIDAY, it1.next());
        assertEquals(SECOND_HOLIDAY, it1.next());
        Set<String> secondDateHolidays = holidays.get(parseDate(SECOND_DATE));
        Iterator<String> it2 = secondDateHolidays.iterator();
        assertEquals(THIRD_HOLIDAY, it2.next());
        Set<String> thirdDateHolidays = holidays.get(parseDate(THIRD_DATE));
        Iterator<String> it3 = thirdDateHolidays.iterator();
        assertEquals(FOURTH_HOLIDAY, it3.next());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenLinesObjectIsNull() throws ParseException {
        lines = null;
        Map<Date, Set<String>> holidays = holidayFileParser.createHolidays(lines, DATE_FORMAT);
        assertTrue("Map of holidays should be empty", holidays.isEmpty());
    }

    @Test(expected = ParseException.class)
    public void shouldThrowParseExceptionWhenDateIsInvalid() throws ParseException {
        lines.add(INVALID_DATE + WHITESPACE + FIFTH_HOLIDAY);
        Map<Date, Set<String>> holidays = holidayFileParser.createHolidays(lines, DATE_FORMAT);
        assertTrue("Map of holidays object should be null", holidays == null);
    }

    @Test(expected = ParseException.class)
    public void shouldThrowParseExceptionWhenDateFormatIsInvalid() throws ParseException {
        Map<Date, Set<String>> holidays = holidayFileParser.createHolidays(lines, INVALID_DATE_FORMAT);
        assertTrue("Map of holidays object should be null", holidays == null);
    }

    private Date parseDate(String date) throws ParseException {
        return new DateParser().parseDate(date, DATE_FORMAT);
    }
}
