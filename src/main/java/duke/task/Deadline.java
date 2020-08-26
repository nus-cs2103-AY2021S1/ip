package duke.task;

import duke.exceptions.DukeStorageException;
import duke.exceptions.DukeTaskCreationException;
import duke.parser.DateParser;

import java.time.LocalDateTime;

/**
 * The {@code Deadline} class represents a task with a specific deadline.
 * Extends the {@link Task} class.
 */
public class Deadline extends Task {

    private static final String DEADLINE_DELIMITER = "/by";

    private LocalDateTime dateTime;

    private Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Returns a {@code Deadline} object with the specified details.
     *
     * @param details the description and deadline of the task.
     * @return a {@code Deadline} object with the specified details.
     * @throws DukeTaskCreationException if format of the specified details is not recognised.
     */
    public static Deadline createTask(String details) throws DukeTaskCreationException {
        if (details == null) {
            throw new DukeTaskCreationException("I need something to work with.");
        }
        String[] detailsArray = details.split(DEADLINE_DELIMITER, 2);
        try {
            String description = detailsArray[0].trim();
            String dateTimeString = detailsArray[1].trim();
            LocalDateTime dateTime = DateParser.parseString(dateTimeString);
            return new Deadline(description, dateTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeTaskCreationException("So you never did plan on doing it huh...");
        }
    }

    /**
     * Decodes an encoded string into a {@code Deadline} object.
     *
     * @param code the encoded string.
     * @return a {@code Deadline} reconstructed from the encoded string.
     * @throws DukeStorageException if format of the code is incorrect.
     */
    public static Deadline decode(String code) throws DukeStorageException {
        if (code.charAt(0) == 'D') {
            String[] content = code.split("\\|", 4);
            if (content.length != 4) {
                throw new DukeStorageException("There are some holes in my memory...");
            }
            Deadline newDeadline = new Deadline(content[3], DateParser.parseString(content[2]));
            if (content[1].equals("Y")) {
                newDeadline.setCompleted();
            } else if (!content[1].equals("N")) {
                throw new DukeStorageException("There are some holes in my memory...");
            }
            return newDeadline;
        } else {
            throw new DukeStorageException("Something doesn't seem right...");
        }
    }

    /**
     * Returns an encoded string representation of this {@code Deadline}.
     *
     * @return an encoded string representation of this {@code Deadline}.
     */
    @Override
    public String encode() {
        return String.format("D|%s|%s|%s", super.completed ? "Y" : "N", DateParser.parseLocalDateTime(dateTime), super.description);
    }

    /**
     * Returns a string representation of this {@code Deadline}.
     *
     * @return a string representation of this {@code Deadline}.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateParser.parseLocalDateTime(dateTime) + ")";
    }
}
