package botbot.tasks;

import botbot.Parser;
import botbot.exceptions.InvalidFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline with a description, deadline and completion status.
 */
public class Deadline extends Task {
    public static final char TYPE_CODE = 'D';
    public static final String FORMAT = "deadline <description> /by <D-M-YYYY HHmm> (eg. 17-3-2020 0945 "
            + "or 3-4-2020 with no time specified)";
    private LocalDateTime by;

    /**
     * Creates a deadline.
     * 
     * @param command Command to create deadline.
     * @throws InvalidFormatException If command is of invalid format.
     */
    public Deadline(String command) throws InvalidFormatException {
        super(TYPE_CODE, extractNameFromCommand(command));
        try {
            by = Parser.parseDateTime(command, "/by");
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException(FORMAT);
        }
    }

    /**
     * Creates a deadline.
     * 
     * @param description Description of deadline.
     * @param isDone Completion status of deadline.
     * @param by Deadline of deadline.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(TYPE_CODE, description, isDone);
        this.by = LocalDateTime.parse(by);
    }

    static String extractNameFromCommand(String command) {
        int index = command.indexOf(" /by ");
        return command.substring(9, index);
    }

    /**
     * Returns the time of the deadline.
     *
     * @return Null.
     */
    @Override
    public String getAt() {
        return null;
    }

    /**
     * Returns the deadline of the deadline.
     *
     * @return Deadline of deadline.
     */
    @Override
    public String getBy() {
        return by.toString();
    }

    @Override
    public String toString() {
        String format = "d MMM yyyy";
        if (by.getHour() != 3 || by.getMinute() != 14 || by.getSecond() != 15 || by.getNano() != 926535898) {
            if (by.getMinute() == 0) {
                format += " ha";
            } else {
                format += " h.mma";
            }
        }
        String byStr = by.format(DateTimeFormatter.ofPattern(format));
        return String.format("[%c] [%s] %s (by: %s)", TYPE_CODE, getStatusIcon(), description, byStr);
    }
}