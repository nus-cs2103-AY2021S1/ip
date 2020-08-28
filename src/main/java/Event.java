import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * represents an event task
 */

public class Event extends Task {
    protected String eventTime;

    public Event(String taskDescription) throws EmptyDescriptionException {
        if (taskDescription.length() <= 6) {
            throw new EmptyDescriptionException("oops! the description of an event cannot be empty");
        } else {
            int space = taskDescription.indexOf(" ");
            int slash = taskDescription.indexOf("/");

            this.task = taskDescription.substring(space + 1, slash);
            this.eventTime = taskDescription.substring(slash + 4);
            this.done = false;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        String eventTime;
        try {
            LocalDate localDate = LocalDate.parse(this.eventTime);
            eventTime = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            eventTime = this.eventTime;
        }

        sb.append("[E]")
                .append(super.toString())
                .append("(at: ").append(eventTime).append(")");
        return sb.toString();
    }

    public String getEventTime() {
        String eventTime;
        try {
            LocalDate localDate = LocalDate.parse(this.eventTime);
            eventTime = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            eventTime = this.eventTime;
        }
        return eventTime;
    }

    @Override
    public String encode() {
        StringBuilder encodedTask = new StringBuilder();

        encodedTask.append("E | ")
                .append(this.isDoneInt() + " | ")
                .append(this.task + "| ")
                .append(this.eventTime);

        return encodedTask.toString();
    }

    public static Event decode(String string) throws EmptyDescriptionException {
        String[] split = string.split(" \\| ");

        String taskDescription = "event " + split[2] +
                " /at " + split[3];

        Event event = new Event(taskDescription);

        if (split[1].contains("1")) {
            event.markAsDone();
        }

        return event;
    }
}
