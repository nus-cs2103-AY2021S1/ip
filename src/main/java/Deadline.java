import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    private final LocalDate deadlineDate;
    private final LocalTime deadlineTime;
    private final String originalArguments;


    public static Deadline createNewDeadline(String argument) throws DukeException {
        String[] deadlineArguments = argument.split(" /by ");

        if (deadlineArguments.length != 2) {
            throw new DukeException("Invalid arguments for a new deadline.");
        }

        String deadlineName = deadlineArguments[0];
        if (deadlineName.isBlank()) {
            throw new DukeException("Deadline name cannot be blank!");
        }

        String deadlineDateTime = deadlineArguments[1];
        if (deadlineDateTime.isBlank()) {
            throw new DukeException("Deadline time cannot be blank!");
        }

        String[] datetime = deadlineDateTime.split(" ");

        LocalDate deadlineDate;
        LocalTime deadlineTime = null;

        try {
            deadlineDate = LocalDate.parse(datetime[0]);

            if (datetime.length == 2) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH[:]mm");
                deadlineTime = LocalTime.parse(datetime[1], dtf);
            }

        } catch (DateTimeParseException e) {
            throw new DukeException("DateTime format is invalid.");
        }

        return new Deadline(deadlineName, argument, deadlineDate, deadlineTime);

    }

    private Deadline(String taskName, String originalArguments, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(taskName);
        this.originalArguments = originalArguments;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
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
        return localDate.isEqual(this.deadlineDate);
    }

    @Override
    public String generateStorageString() {
        return String.format("DEADLINE | %s | %s", isDone ? "TRUE" : "FALSE", originalArguments);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), printDateTime());
    }
}
