import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Event extends Task {
    protected String at;
    protected LocalDate atDate;
    protected LocalDateTime atDateTime;

    public Event(String description, String at) {
        super(description);
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

    private String getFormattedAt() {
        if (this.atDateTime != null) {
            return this.atDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        } else if (this.atDate != null) {
            return this.atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return this.at;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getFormattedAt() + ")";
    }
}