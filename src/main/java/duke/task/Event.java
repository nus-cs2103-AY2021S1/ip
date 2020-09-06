package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * Class representing an event.
 */
public class Event extends Task {

    private LocalDate taskDate;
    private LocalTime taskTime;

    /**
     * Creates a brand new {@code Event}.
     * @param description Description of the event.
     * @param at Time that the event is happening at.
     */
    public Event(String description, String at) {
        super(description);
        this.date = at;
        this.taskType = "E";

        String[] dateArguments = at.split(" ");

        try {
            taskDate = LocalDate.parse(dateArguments[0]);

            if (dateArguments.length == 2) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH[:]mm");
                taskTime = LocalTime.parse(dateArguments[1], dtf);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Date and time format is invalid.");
        }
    }

    /**
     * Create an {@code Event} from existing data.
     * @param isDone Event completion status.
     * @param description Description of the event.
     * @param at Time that the event is happening at.
     */
    public Event(boolean isDone, String description, String at) {
        this(description, at);

        if (isDone) {
            this.markDone();
        }
    }


    private String formatTaskTime() {
        String output = taskDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        if (taskTime != null) {
            output += ", " + taskTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
        }
        return output;
    }


    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), formatTaskTime());
    }
}
