import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Event extends Task {

    private final LocalDate startDate;
    private final LocalTime startTime;

    public static Event createNewEvent(String argument) throws DukeException {
        String[] eventArguments = argument.split(" /at ");

        if (eventArguments.length != 2) {
            throw new DukeException("Invalid arguments for a new event.");
        }

        String eventName = eventArguments[0];
        if (eventName.isBlank()) {
            throw new DukeException("Event name cannot be blank!");
        }

        String startDateTime = eventArguments[1];
        if (startDateTime.isBlank()) {
            throw new DukeException("Event time cannot be blank!");
        }
        
        String[] datetime = startDateTime.split(" ");
        
        LocalDate startDate;
        LocalTime startTime = null;
        
        try {
            startDate = LocalDate.parse(datetime[0]);

            if (datetime.length == 2) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH[:]mm");
                startTime = LocalTime.parse(datetime[1], dtf);
            }
            
        } catch (DateTimeParseException e) {
            throw new DukeException("DateTime format is invalid.");
        }

        return new Event(eventName, startDate, startTime);
    }
    
    private String printDateTime() {
        
        String output = startDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        
        if (startTime != null) {
            output += ", " + startTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
        }
        
        return output;
    }

    private Event(String taskName, LocalDate startDate, LocalTime startTime) {
        super(taskName);
        this.startDate = startDate;
        this.startTime = startTime;
    }

    @Override
    public boolean isOnSameDay(LocalDate localDate) {
        return localDate.isEqual(this.startDate);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), printDateTime());
    }
}
