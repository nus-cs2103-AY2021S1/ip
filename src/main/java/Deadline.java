import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;


public class Deadline extends Task implements Serializable {
    protected LocalDateTime deadlineDateTime;
    protected String deadlineName;

    public Deadline(String description) {
        super(description);
        String[] descriptionArray = description.split("/by");
        if(descriptionArray.length == 1) { //no "/at" present
            throw new IllegalArgumentException("Invalid input, no deadline stated");
        } else if(descriptionArray.length > 2) {
            throw new IllegalArgumentException("Invalid input, multiple deadlines stated");
        }
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(" dd/MM/yyyy HH:mm");
        deadlineDateTime = LocalDateTime.parse(descriptionArray[1], inputFormat);
        deadlineName = descriptionArray[0];
    }


    public String getDeadlineDateTime() throws DateTimeParseException {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a", Locale.ENGLISH);
        return deadlineDateTime.format(outputFormat);
    }

//    public void getDeadlineOnThisDay(String deadlineDate) {
//        if(deadlineDate == getDeadlineDateTime()) {
//            System.out.println(taskName);
//        }
//    }

    @Override
    public String toString() {
            return "[D]" + "[" + getStatusIcon() + "] " + deadlineName + " (by: " + getDeadlineDateTime() + ")";
    }
}