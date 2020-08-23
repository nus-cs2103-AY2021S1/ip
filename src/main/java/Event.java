import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Event extends Task implements Serializable {

   protected LocalDateTime eventDateTime;
   protected String eventName;

    public Event(String description) {
        super(description);
        String[] descriptionArray = description.split("/at");
        if(descriptionArray.length == 1) { //no "/at" present
            throw new IllegalArgumentException("Invalid input, no deadline stated");
        } else if(descriptionArray.length > 2) {
            throw new IllegalArgumentException("Invalid input, multiple deadlines stated");
        }
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(" dd/MM/yyyy HH:mm");
        eventDateTime = LocalDateTime.parse(descriptionArray[1], inputFormat);
        eventName = descriptionArray[0];
    }

    public String getEventDateTime() throws DateTimeParseException {

        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(" dd-MMM-yyyy hh:mm a", Locale.ENGLISH);
        return eventDateTime.format(outputFormat);
    }

//    public void getEventOnThisDay(String deadlineDate) {
//        if(eventDateTime == getEventDateTime()) {
//            System.out.println(eventName);
//        }
//    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + eventName + " (at:" + getEventDateTime() + ")";
    }
}