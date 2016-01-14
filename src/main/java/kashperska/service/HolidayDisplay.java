package kashperska.service;

import kashperska.parser.DateParser;
import kashperska.utils.DateUtils;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public class HolidayDisplay {

    private Map<Date, Set<String>> holidays;

    private static final String DATE_FORMAT = "EEE, dd MMM";
    private final static String nl = System.getProperty("line.separator");

    public HolidayDisplay(Map<Date, Set<String>> holidays) {
        if (holidays == null) {
            throw new IllegalArgumentException("Map should contain holidays");
        }
        this.holidays = holidays;
    }

    public String showTodayHolidays() {
        return outputHolidaysNames(holidays.get(DateUtils.getTodayDate()));
    }

    public String showTomorrowsHolidays() {
        return outputHolidaysNames(holidays.get(DateUtils.getDateInDays(1)));
    }

    public String showNearFutureHolidays(int days, boolean inclusiveToday) {
        StringBuilder result = new StringBuilder();

        if (inclusiveToday) {
            result.append(getDate(DateUtils.getTodayDate())).append(showTodayHolidays());
        }

        for (int i = 1; i <= days; i++) {
            Date date = DateUtils.getDateInDays(i);
            result.append(getDate(date)).append(outputHolidaysNames(holidays.get(date)));
        }
        return result.toString();
    }

    private String outputHolidaysNames(Set<String> holidaysNames) {
        StringBuilder result = new StringBuilder();

        if (holidaysNames != null && !holidaysNames.isEmpty()) {
            for (String name : holidaysNames) {
                result.append(name).append(nl);
            }
        }

        return result.toString();
    }

    private String getDate(Date date) {
        return new DateParser().formatDate(date, DATE_FORMAT) + " - ";
    }
}
