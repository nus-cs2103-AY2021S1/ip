import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public Deadline(String description, boolean isDone, LocalDate timestamp) {
        super(description, "D", isDone, timestamp);
    }

    public static Deadline newDeadline(String raw) throws ChatbotException {
        if (raw.length() == 0) {
            throw new ChatbotException("Ooopsss (>.>) Deadline cannot be empty!!");
        }
        String description = raw.split("/by")[0].trim();
        LocalDate timestamp;
        try {
            String dateString = raw.split("/by")[1].trim();
            timestamp = LocalDate.parse(dateString);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChatbotException("Ooopsss (>.>) Deadline? By when??!!");
        } catch (DateTimeParseException e) {
            throw new ChatbotException("Please enter a valid date (yyyy-mm-dd).");
        }
        return new Deadline(description, false, timestamp);
    }

    @Override
    public Deadline markDone() {
        return new Deadline(this.description, true, this.timestamp);
    }

    @Override
    public String toString() {
        String formattedDate = this.timestamp.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return super.toString() + " (by: " + formattedDate + ")";
    }
}
