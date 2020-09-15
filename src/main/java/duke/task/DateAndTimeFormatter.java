package duke.task;

import duke.io.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Format date and time.
 */
public class DateAndTimeFormatter {
    ArrayList<Object> dateAndTime;
    
    protected DateAndTimeFormatter(String by) {
        dateAndTime = dateAndTimeFormatter(by);
    };

    /**
     * Return specified deadline or event time in a different format 
     * if valid date and time are given.
     * For ease of readability.
     *
     * @param date Date to format.
     * @return ArrayList of LocalDate object and String that represents 12 hour time.
     */
    private ArrayList<Object> dateAndTimeFormatter(String date) {
        return Parser.dateAndTimeFormatter(date);
    }
    
    public LocalDate getDate() {
        if (dateAndTime.get(0) != null) {
            return (LocalDate) dateAndTime.get(0);
        }
        return null;
    }
    
    public String getTime() {
        if (dateAndTime.get(1) != null) {
            return (String) dateAndTime.get(1);
        }
        return null;
    }

    public String getFormattedBy(LocalDate localDate, String time) {
        String formattedBy = "";
        if (localDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
            formattedBy += localDate.format(formatter);
        }
        if (time != null) {
            formattedBy += localDate != null ? " " + time : time;
        }
        if (formattedBy.length() != 0) {
            formattedBy = "(" + formattedBy + ")";
        }
        return formattedBy;
    }
    
    
    
}
