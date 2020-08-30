package botbot.tasks;

import botbot.Parser;
import botbot.exceptions.InvalidFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime at;
    public static final char TYPE_CODE = 'E';
    public static final String FORMAT = "event <description> /at <D-M-YYYY HHmm> (eg. 17-3-2020 0945 "
            + "or 3-4-2020 with no time specified)";

    public Event(String command) throws InvalidFormatException {
        super(TYPE_CODE, extractNameFromCommand(command));
        try {
            at = Parser.parseDateTime(command, "/at");
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException(FORMAT);
        }
    }

    public Event(String description, boolean isDone, String at) {
        super(TYPE_CODE, description, isDone);
        this.at = LocalDateTime.parse(at);
    }

    static String extractNameFromCommand(String command) {
        int index = command.indexOf(" /at ");
        return command.substring(6, index);
    }
    
    @Override
    public String getAt() {
        return at.toString();
    }
    
    @Override
    public String getBy() {
        return null;
    }
    
    @Override
    public String toString() {
        String format = "d MMM yyyy";
        if (at.getHour() != 3 || at.getMinute() != 14 || at.getSecond() != 15 || at.getNano() != 926535898) {
            if (at.getMinute() == 0) {
                format += " ha";
            } else {
                format += " h.mma";
            }
        }
        String atStr = at.format(DateTimeFormatter.ofPattern(format));
        return String.format("[%c] [%s] %s (at: %s)", TYPE_CODE, getStatusIcon(), description, atStr);
    }
}