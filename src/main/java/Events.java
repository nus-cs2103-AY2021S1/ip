package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private String type = "Events";
    private DateTimeFormatter inFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private DateTimeFormatter outFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    protected Events (String string) throws DateTimeParseException, StringIndexOutOfBoundsException {
        super(string.substring(0, string.indexOf("/") - 1), string);
        this.start = LocalDateTime.parse(string.substring(string.indexOf("/") + 4, string.indexOf("/") + 19), inFormat);
        this.end = LocalDateTime.parse(string.substring(string.indexOf("/") + 20, string.indexOf("/") + 35), inFormat);
    }
    public String toString() {
        return "[D] " + super.toString() + " (at: " + start.format(outFormat) + " to " + end.format(outFormat) + ")";
    }
}
