package duke.task;

import java.util.Date;

/**
 * This class contains helper functions for formatting Dates for display.
 */
class DateHelper {

    /**
     * Formats a Date in the following format: Mon 12 Jan 2020 17:00
     *
     * @param date Date to format.
     * @return String with the formatted Date.
     */
    static String formatDate(Date date) {
        return String.format("%ta %<td %<tb %<tY %<tH:%<tM", date);
    }

    /**
     * Formats a date range. If the start and end Dates are on the same day, the date will only be printed
     * once (eg. Mon 12 Jan 2020 17:00-19:00). Otherwise, the start and end Dates will each be formatted using
     * the formatDate function.
     *
     * @param start Start of the date range.
     * @param end End of the date range.
     * @return String with the formatted Date.
     */
    static String formatDateRange(Date start, Date end) {
        String datePattern = "%td %<tb %<tY";
        if (String.format(datePattern, start).equals(String.format(datePattern, end))) {
            return String.format("%ta %<td %<tb %<tY", start) + " " + String.format("%tH:%<tM-%tH:%<tM",
                    start, end);
        } else {
            return formatDate(start) + " - " + formatDate(end);
        }
    }
}
