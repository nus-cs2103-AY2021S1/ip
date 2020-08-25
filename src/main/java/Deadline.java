import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Deadline extends Task {
    protected String by;
    protected LocalDate byDate;
    protected LocalDateTime byDateTime;

    /**
     * Constructor for Deadline.
     *
     * @param description Deadline task description
     * @param by          Deadline for task.
     **/
    public Deadline(String description, String by) {
        super(description);

        this.byDateTime = tryParseDateTime(by);
        if (byDateTime == null) {
            this.byDate = tryParseDate(by);
        }
        if (byDate == null) {
            this.by = by;
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

    public String generateByFormat() {
        if (byDateTime != null) {
            return byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, K:mm a"));
        } else if (byDate != null) {
            return byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            return by;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + generateByFormat() + ")";
    }
}
