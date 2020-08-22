package ip.src.main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Deadline extends Task {
    private String by;
    private LocalDate localDate;
    private String time;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        ArrayList<Object> dateAndTime = dateAndTimeFormatter(by);
        localDate = null;
        time = null;
        if (dateAndTime.get(0) != null) {
            localDate = (LocalDate) dateAndTime.get(0);
        } 
        if (dateAndTime.get(1) != null) {
            time = (String) dateAndTime.get(1);
        }
    }

    @Override
    public String toString() {
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
        return "[D]" + super.toString() + "(by: " + by + ") " + formattedBy;
    }
}
