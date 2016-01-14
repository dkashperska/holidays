package kashperska.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateParser {

    private static Map<String, DateFormat> formatters = new HashMap<>();

    public Date parseDate(String date, String format) throws ParseException {
        return getFormatter(format).parse(date);
    }

    public String formatDate(Date date, String format) {
        return getFormatter(format).format(date);
    }

    public DateFormat getFormatter(String format) {
        DateFormat formatter = formatters.get(format);
        if (formatter == null) {
            synchronized (formatters) {
                formatter = formatters.get(format);

                if (formatter == null) {
                    formatter = new SimpleDateFormat(format);
                    formatters.put(format, formatter);
                }
            }
        }

        return formatter;
    }

}
