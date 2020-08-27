import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        
        // Check for format 
        try {
            String parsableBy = by.replace(" ", "T");
            this.by = LocalDateTime.parse(parsableBy);
        } catch (Exception e){
            throw new DukeException("invalidDeadlineDateTime");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm");
        String datetime = by.format(formatter);
        String day = by.getDayOfWeek().getDisplayName(TextStyle.SHORT, new Locale("en"));
        return "[D]" + super.toString() + " (by: " + day + ", " + datetime + ")";
    }
}