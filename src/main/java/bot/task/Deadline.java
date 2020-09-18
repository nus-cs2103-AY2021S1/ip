package bot.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bot.util.DateParser;
import bot.util.InvalidInputException;

/**
 * A special type of task characterised by the input "/by" which implies the importance of the deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for Deadline with isDone status as false.
     *
     * @param name Name of deadline.
     * @param deadline The date of which the task is to be done by.
     * @throws InvalidInputException Invalid input.
     */
    public Deadline(String name, String deadline) throws InvalidInputException {
        super(name);
        String dateFormat = DateParser.determineDateFormat(deadline);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat.strip());
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    /**
     * Constructor for Deadline with its isDone status input.
     *
     * @param name Name of deadline.
     * @param deadline The date of which the task is to be done by.
     * @param isDone The task is completed.
     * @throws InvalidInputException Invalid input.
     */
    public Deadline(String name, String deadline, boolean isDone) throws InvalidInputException {
        super(name, isDone);
        String dateFormat = DateParser.determineDateFormat(deadline);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat.strip());
        this.deadline = LocalDateTime.parse(deadline.strip(), formatter);
    }

    /**
     * Serialises the object.
     * @return A string that is formatted to be read and stored in Storage.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "D" + " | " + super.toFileFormat() + " | " + this.deadline.format(formatter)
                + "\n";
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")));
    }
}
