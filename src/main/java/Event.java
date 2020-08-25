import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    public Event(String description, boolean isDone, LocalDate timestamp) {
        super(description, "E", isDone, timestamp);
    }

    public static Event newEvent(String raw) throws ChatbotException {
        if (raw.length() == 0) {
            throw new ChatbotException("Event cannot be empty!!");
        }

        String description = raw.split("/at")[0].trim();
        LocalDate timestamp;

        try {
            String dateString = raw.split("/at")[1].trim();
            timestamp = LocalDate.parse(dateString);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChatbotException("Event? At what date??!!");
        } catch (DateTimeParseException e) {
            throw new ChatbotException(Message.INVALID_DATE);
        }

        return new Event(description, false, timestamp);
    }

    @Override
    public Event markDone() {
        return new Event(this.description, true, this.timestamp);
    }

    @Override
    public String toString() {
        String formattedDate = this.timestamp.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return super.toString() + " (at: " + formattedDate + ")";
    }
}
