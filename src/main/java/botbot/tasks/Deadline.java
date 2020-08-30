package botbot.tasks;

import botbot.Parser;
import botbot.exceptions.InvalidFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime by;
    public static final char TYPE_CODE = 'D';
    public static final String FORMAT = "deadline <description> /by <D-M-YYYY HHmm> (eg. 17-3-2020 0945 "
            + "or 3-4-2020 with no time specified)";

    public Deadline(String command) throws InvalidFormatException {
        super(TYPE_CODE, extractNameFromCommand(command));
        try {
            by = Parser.parseDateTime(command, "/by");
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException(FORMAT);
        }
    }

    public Deadline(String description, boolean isDone, String by) {
        super(TYPE_CODE, description, isDone);
        this.by = LocalDateTime.parse(by);
    }

    static String extractNameFromCommand(String command) {
        int index = command.indexOf(" /by ");
        return command.substring(9, index);
    }

    @Override
    public String getAt() {
        return null;
    }

    @Override
    public String getBy() {
        return by.toString();
    }

    @Override
    public String toString() {
        String format = "d MMM yyyy";
        if (by.getHour() != 3 || by.getMinute() != 14 || by.getSecond() != 15 || by.getNano() != 926536) {
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