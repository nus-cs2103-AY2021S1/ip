import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Event extends Task {

   protected String at;

    public Event(String description) {
        super(description);
    }
    String[] descriptionArray = description.split("/at");
    String eventDateTime = descriptionArray[1];
    String eventName = descriptionArray[0];

    public String getEventDateTime() throws DateTimeParseException {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(" dd/MM/yyyy HH:mm");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(" dd-MMM-yyyy hh:mm a", Locale.ENGLISH);
        return LocalDateTime.parse(eventDateTime, inputFormat).format(outputFormat);
    }

    public void getEventOnThisDay(String deadlineDate) {
        if(eventDateTime == getEventDateTime()) {
            System.out.println(eventName);
        }
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + eventName + " (at:" + getEventDateTime() + ")";
    }
}