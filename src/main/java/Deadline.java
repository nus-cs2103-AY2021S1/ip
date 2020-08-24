import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;


public class Deadline extends Task {

   protected String by;

    public Deadline(String description) {
        super(description);
    }

    String[] descriptionArray = description.split("/by");
    String deadlineDateTime = descriptionArray[1];
    String taskName = descriptionArray[0];

    public String getDeadlineDateTime() throws DateTimeParseException {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(" dd/MM/yyyy HH:mm");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a", Locale.ENGLISH);
        return LocalDateTime.parse(deadlineDateTime, inputFormat).format(outputFormat);
    }

    public void getDeadlineOnThisDay(String deadlineDate) {
        if(deadlineDate == getDeadlineDateTime()) {
            System.out.println(taskName);
        }
    }

    @Override
    public String toString() {
            return "[D]" + "[" + getStatusIcon() + "] " + taskName + " (by: " + getDeadlineDateTime() + ")";
    }
}