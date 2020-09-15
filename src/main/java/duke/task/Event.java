package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import duke.DateTime;
import duke.DukeException;

/**
 * Encapsulates data and methods specific to Event tasks.
 */
public class Event extends Task {

    private final LocalDate startDate;
    private final LocalTime startTime;
    private final String originalArguments;

    private Event(String taskName, String originalArguments, LocalDate startDate, LocalTime startTime) {
        super(taskName);
        this.originalArguments = originalArguments;
        this.startDate = startDate;
        this.startTime = startTime;
    }

    /**
     * Creates a new instance of the Event class if the argument provided is valid.
     *
     * @param argument Argument entered by user to create Event class.
     * @return New instance of the Event class.
     * @throws DukeException If any part of the input argument is invalid.
     */
    public static Event createNewEvent(String argument) throws DukeException {

        assert argument != null : "Task argument cannot be null";

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

        DateTime dateTime = DateTime.parseFromString(startDateTime);
        return new Event(eventName, argument, dateTime.getDate(), dateTime.getTime());
    }

    private String printDateTime() {

        String output = startDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

        if (startTime != null) {
            output += ", " + startTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
        }

        return output;
    }

    /**
     * Generates a single line string that will be saved in storage.
     *
     * @return String to be saved in storage.
     */
    @Override
    public String generateStorageString() {
        return String.format("EVENT | %s | %s", isDone ? "TRUE" : "FALSE", originalArguments);
    }

    @Override
    public boolean isOnSameDay(LocalDate localDate) {
        assert localDate != null : "Local date argument cannot be null";
        return localDate.isEqual(this.startDate);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), printDateTime());
    }
}
