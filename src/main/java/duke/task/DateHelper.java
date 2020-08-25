package duke.task;

import java.util.Date;

class DateHelper {
    static String formatDate(Date date) {
        return String.format("%ta %<td %<tb %<tY %<tH:%<tM", date);
    }

    static String formatDateRange(Date start, Date end) {
        String datePattern = "%td %<tb %<tY";
        if (String.format(datePattern, start).equals(String.format(datePattern, end))) {
            return String.format("%ta %<td %<tb %<tY", start) + " " + String.format("%tH:%<tM-%tH:%<tM", start, end);
        } else {
            return formatDate(start) + " - " + formatDate(end);
        }
    }
}
