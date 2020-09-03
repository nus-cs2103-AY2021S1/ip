package nekochan.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import nekochan.exceptions.NekoException;
import nekochan.exceptions.NekoStorageException;
import nekochan.exceptions.NekoTaskCreationException;
import nekochan.parser.DateParser;

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
     * @throws NekoTaskCreationException if format of the specified details is not recognised.
     */
    public static Deadline createTask(String details) throws NekoTaskCreationException {
        if (details == null) {
            throw new NekoTaskCreationException("I need something to work with.");
        }
        try {
            String description = details.substring(0, details.lastIndexOf(DEADLINE_DELIMITER)).trim();
            String dateTimeString = details.substring(details.lastIndexOf(DEADLINE_DELIMITER) + 2).trim().toLowerCase();
            LocalDateTime dateTime = DateParser.parseString(dateTimeString);
            return new Deadline(description, dateTime);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NekoTaskCreationException("So you never did plan on doing it huh...");
        }
    }

    /**
     * Decodes an encoded string into a {@code Deadline} object.
     *
     * @param code the encoded string.
     * @return a {@code Deadline} reconstructed from the encoded string.
     * @throws NekoStorageException if format of the code is incorrect.
     */
    public static Deadline decode(String code) throws NekoStorageException {
        if (code.charAt(0) != 'D') {
            throw new NekoStorageException("Something doesn't seem right...");
        }
        String[] content = code.split("\\|", 4);
        if (content.length != 4) {
            throw new NekoStorageException("There are some holes in my memory...");
        }
        Deadline newDeadline = new Deadline(content[3], DateParser.parseString(content[2]));
        if (content[1].equals("Y")) {
            newDeadline.setCompleted();
        } else if (!content[1].equals("N")) {
            throw new NekoStorageException("There are some holes in my memory...");
        }
        return newDeadline;
    }

    /**
     * Returns an encoded string representation of this {@code Deadline}.
     *
     * @return an encoded string representation of this {@code Deadline}.
     */
    @Override
    public String encode() {
        return String.format("D|%s|%s|%s", super.isCompleted ? "Y" : "N",
                DateParser.parseLocalDateTime(dateTime),
                super.description);
    }

    /**
     * Returns true if this {@code Deadline} matches the specified {@code searchParameter}.
     *
     * @param searchParameter the string to test against.
     * @return true if this {@code Deadline} matches the specified {@code searchParameter}.
     */
    @Override
    public boolean match(String searchParameter) {
        try {
            LocalDate searchDate = DateParser.parseString(searchParameter).toLocalDate();
            return searchDate.isEqual(dateTime.toLocalDate());
        } catch (NekoException e) {
            return searchParameter.contains(description) || description.contains(searchParameter);
        }
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
