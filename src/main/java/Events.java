package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private LocalDateTime at;
    private String type = "Events";
    private DateTimeFormatter inFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private DateTimeFormatter outFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    protected Events (String string) {
        super(string.substring(0, string.indexOf("/") - 1), string);
        this.at = LocalDateTime.parse(string.substring(string.indexOf("/") + 4), inFormat);
    }
    public String toString() {
        return "[D] " + super.toString() + " (at: " + at.format(outFormat) + ")";
    }
}
