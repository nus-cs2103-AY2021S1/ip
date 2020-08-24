import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String at;
    protected LocalDate atDate;
    protected LocalDateTime atDateTime;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.setAtDateAndTime();
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
        this.setAtDateAndTime();
    }

    private void setAtDateAndTime() {
        try {
            this.atDateTime = LocalDateTime.parse(this.at);
            this.atDate = this.atDateTime.toLocalDate();
        } catch (DateTimeParseException e) {
            try {
                this.atDate = LocalDate.parse(this.at);
            } catch (DateTimeParseException ignored) { }
        }
    }

    public String getAt() {
        return this.at;
    }

    private String getFormattedAt() {
        if (this.atDateTime != null) {
            return this.atDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm:ss a"));
        } else if (this.atDate != null) {
            return this.atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return this.at;
        }
    }

    public boolean isOnDate(LocalDate date) {
        if (this.atDate != null) {
            return date.compareTo(this.atDate) == 0;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getFormattedAt() + ")";
    }
}