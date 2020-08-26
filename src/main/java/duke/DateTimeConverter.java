package main.java.duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateTimeConverter {

    public DateTimeFormatter dtf;

    public DateTimeConverter(FormatStyle dateStyle, FormatStyle timeStyle) {
        this.dtf = DateTimeFormatter.ofLocalizedDateTime(dateStyle, timeStyle);
    }

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
            String[] date_seg = date.split("-", 3);

            if (date_seg[0].length() != 4) {
                String temp = date_seg[0];
                date_seg[0] = date_seg[2];
                date_seg[2] = temp;
            }
            LocalDate ld = LocalDate.parse(date_seg[0] + "-" + date_seg[1] + "-" + date_seg[2]);

            LocalDateTime ldt = LocalDateTime.of(ld, lt);
            return ldt.format(dtf);
        } catch (Exception ex) {
            HandleException.handleException(DukeException.ExceptionType.improper_dateTime);
            return dateTime;
        }
    }
}
