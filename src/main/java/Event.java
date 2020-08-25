import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Event extends Task {
    protected String at;
    protected LocalDate atDate;
    protected LocalDateTime atDateTime;

    /**
     * Constructor for Event.
     *
     * @param description Event task description
     * @param at          Time frame for event.
     **/
    public Event(String description, String at) {
        super(description);

        this.atDateTime = tryParseDateTime(at);
        if (atDateTime == null) {
            this.atDate = tryParseDate(at);
        }
        if (atDate == null) {
            this.at = at;
        }
    }

    public LocalDateTime tryParseDateTime(String dateString) {
        List<String> formatStrings = Arrays.asList("yyyy-MM-dd HHmm", "yyyy-MM-d HHmm", "dd/MM/yyyy HHmm", "d/MM/yyyy HHmm",
                "dd-MM-yyyy HHmm", "d-MM-yyyy HHmm");
        for (String formatString : formatStrings) {
            try {
                return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(formatString));
            } catch (DateTimeParseException e) {
            }
        }
        return null;
    }

    public LocalDate tryParseDate(String dateString) {
        List<String> formatStrings = Arrays.asList("yyyy-MM-dd", "yyyy-MM-d", "dd/MM/yyyy", "d/MM/yyyy", "dd-MM-yyyy", "d-MM-yyyy");
        for (String formatString : formatStrings) {
            try {
                return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(formatString));
            } catch (DateTimeParseException e) {
            }
        }
        return null;
    }

    public String generateAtFormat() {
        if (atDateTime != null) {
            return atDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, K:mm a"));
        } else if (atDate != null) {
            return atDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            return at;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + generateAtFormat() + ")";
    }
}
