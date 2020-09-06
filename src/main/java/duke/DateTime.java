package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DateTime {

    private LocalDate date;
    private Optional<LocalTime> optionalTime;

    public DateTime(LocalDate date, Optional<LocalTime> optionalTime) {
        this.date = date;
        this.optionalTime = optionalTime;
    }

    public String getFileFormattedDate() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getFileFormattedDateTime() {
        return this.optionalTime.map(x -> getFileFormattedDate() + " | " + x.format(DateTimeFormatter.ofPattern(
                "HHmm"))).orElse(getFileFormattedDate());
    }

    public String getPrintFormattedDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getPrintFormattedTime() {
        return this.optionalTime.map(x -> x.format(DateTimeFormatter.ofPattern("HHmma"))).orElse("");
    }
}
