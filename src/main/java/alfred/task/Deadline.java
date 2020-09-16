package alfred.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import alfred.AlfredException;
import alfred.DateTime;

/**
 * Encapsulates data and methods specific to Deadline tasks.
 */
public class Deadline extends Task {

    private final LocalDate deadlineDate;
    private final LocalTime deadlineTime;
    private final String originalArguments;

    /**
     * Creates a new Deadline task.
     * @param taskName Name of the deadline task.
     * @param originalArguments Original arguments passed in by the user.
     * @param deadlineDate Date that the task is due.
     * @param deadlineTime Time that the task is due.
     */
    private Deadline(String taskName, String originalArguments, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(taskName);
        this.originalArguments = originalArguments;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Creates a new instance of the Deadline class if the argument provided is valid.
     *
     * @param argument Argument entered by user to create Deadline class.
     * @return New instance of the Deadline class.
     * @throws AlfredException If any part of the input argument is invalid.
     */
    public static Deadline createNewDeadline(String argument) throws AlfredException {

        assert argument != null : "Task argument cannot be null";

        String[] deadlineArguments = argument.split(" /by ");

        if (deadlineArguments.length != 2) {
            throw new AlfredException("Invalid arguments for a new deadline.");
        }

        String deadlineName = deadlineArguments[0];
        if (deadlineName.isBlank()) {
            throw new AlfredException("Deadline name cannot be blank!");
        }

        String deadlineDateTime = deadlineArguments[1];
        if (deadlineDateTime.isBlank()) {
            throw new AlfredException("Deadline time cannot be blank!");
        }

        DateTime dateTime = DateTime.parseFromString(deadlineDateTime);
        return new Deadline(deadlineName, argument, dateTime.getDate(), dateTime.getTime());
    }

    private String printDateTime() {

        String output = deadlineDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

        if (deadlineTime != null) {
            output += ", " + deadlineTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
        }

        return output;
    }

    @Override
    public boolean isOnSameDay(LocalDate localDate) {
        assert localDate != null : "Local date argument cannot be null";
        return localDate.isEqual(this.deadlineDate);
    }

    /**
     * Generates a single line string that will be saved in storage.
     *
     * @return String to be saved in storage.
     */
    @Override
    public String generateStorageString() {
        return String.format("DEADLINE | %s | %s", isDone ? "TRUE" : "FALSE", originalArguments);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), printDateTime());
    }
}
