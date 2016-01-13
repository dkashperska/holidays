package kashperska.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateParser {

    private static Map<String, DateFormat> formatters = new HashMap<>();

    public Date parseDate(String date, String format) {
        Date parsedDate = null;
        DateFormat formatter = getFormatter(format);

        try{
            parsedDate = formatter.parse(date);
        } catch (ParseException e){
            e.printStackTrace();
        }

        return parsedDate;
    }

    public String convertDate(Date date, String format){
        DateFormat formatter = getFormatter(format);
        return formatter.format(date);
    }

    public DateFormat getFormatter(String format){
        DateFormat formatter = formatters.get(format);

        if(formatter == null){
            formatter = new SimpleDateFormat(format);
            formatters.put(format,formatter);
        }
        return formatter;
    }

}
