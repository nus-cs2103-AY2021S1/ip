package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.exceptions.DukeException;
import duke.exceptions.DukeStorageException;
import duke.exceptions.DukeTaskCreationException;
import duke.parser.DateParser;

/**
 * The {@code Deadline} class represents a task with a specific deadline.
 * Extends the {@link Task} class.
 */
public class Deadline extends Task {

    private static final String DEADLINE_DELIMITER = "by";

    private LocalDateTime dateTime;

    private Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Returns a {@code Deadline} object with the specified {@code details}.
     *
     * @param details the description and deadline of the task.
     * @return a {@code Deadline} object with the specified details.
     * @throws DukeTaskCreationException if format of the specified details is not recognised.
     */
    public static Deadline createTask(String details) throws DukeTaskCreationException {
        if (details == null) {
            throw new DukeTaskCreationException("I need something to work with.");
        }
        try {
            String description = details.substring(0, details.lastIndexOf(DEADLINE_DELIMITER)).trim();
            String dateTimeString = details.substring(details.lastIndexOf(DEADLINE_DELIMITER) + 2).trim().toLowerCase();
            LocalDateTime dateTime = DateParser.parseString(dateTimeString);
            return new Deadline(description, dateTime);
        } catch (StringIndexOutOfBoundsException e) {
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
        return String.format("D|%s|%s|%s", super.completed ? "Y" : "N",
                DateParser.parseLocalDateTime(dateTime),
                super.description);
    }

    @Override
    public boolean match(String searchParameter) {
        try {
            LocalDate searchDate = DateParser.parseString(searchParameter).toLocalDate();
            if (searchDate.isEqual(dateTime.toLocalDate())) {
                return true;
            }
        } catch (DukeException e) {
            // We attempt to parse the string as a LocalDate and compare it to the deadline date,
            // but upon failure, we perform a comparison with the deadline description.
        }
        return searchParameter.contains(description) || description.contains(searchParameter);
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
