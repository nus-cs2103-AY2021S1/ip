package duke.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateTime {
    private String originalInput;
    private LocalDate date;
    private LocalTime time;

    public DateTime(String originalInput, LocalDate date) {
        this.originalInput = originalInput;
        this.date = date;
    }

    public DateTime(String originalInput, LocalDate date, LocalTime time) {
        this(originalInput, date);
        this.time = time;
    }

    public String getOriginalInput() {
        return originalInput;
    }

    @Override
    public String toString() {
        String output = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

        if (time != null) {
            output += ", " + time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
        }

        return output;
    }
}
