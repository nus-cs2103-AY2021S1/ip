package main.java.duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateTimeConverter {

    /** DateTimeFormatter object for formatting purposes */
    protected DateTimeFormatter dtf;


    /**
     * Constructor of DateTimeConverter class.
     *
     * @param dateStyle  FormatStyle for LocalDate.
     * @param timeStyle  FormatStyle for LocalTime.
     */
    public DateTimeConverter(FormatStyle dateStyle, FormatStyle timeStyle) {
        this.dtf = DateTimeFormatter.ofLocalizedDateTime(dateStyle, timeStyle);
    }


    /**
     * Returns Processed date and time from user input.
     *
     * @param dateTime  User input for date and time of events.
     * @return Formatted date and time.
     */
    public String processTime(String dateTime) {
        String date;
        String time;
        try {
            if (dateTime.length() > 11) {
                String[] parts = dateTime.split(" ", 2);
                time = parts[0].length() == 4 ? parts[0] : parts[1];
                date = parts[0].length() == 4 ? parts[1] : parts[0];
            } else {
                date = dateTime;
                time = "2359";
            }
            LocalTime lt = LocalTime.of(
                    Integer.parseInt(time.substring(0, 2)), Integer.parseInt(time.substring(2)));

            date = date.replaceAll("\\D", "-");
            String[] dateSeg = date.split("-", 3);
            if (dateSeg[0].length() != 4) {
                String temp = dateSeg[0];
                dateSeg[0] = dateSeg[2];
                dateSeg[2] = temp;
            }
            LocalDate ld = LocalDate.parse(dateSeg[0] + "-" + dateSeg[1] + "-" + dateSeg[2]);

            LocalDateTime ldt = LocalDateTime.of(ld, lt);
            return ldt.format(dtf);
        } catch (Exception ex) {
            HandleException.handleException(DukeException.ExceptionType.IMPROPER_DATETIME);
            return dateTime;
        }
    }
}