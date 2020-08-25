import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime at;
    protected static final String FORMAT = "event <description> /at <D-M-YYYY HHmm> (eg. 17-3-2020 0945 "
            + "or 3-4-2020 with no time specified)";
    private static final String NO_TIME_FLAG = String.format(" 0%.0f", Math.PI * Math.pow(10, 10));

    Event(String command) throws InvalidFormatException {
        super(extractNameFromCommand(command));
        int index = command.indexOf("/at ") + 4;
        String dateStr = command.substring(index).strip();
        if (dateStr.length() < 13) {
            dateStr += NO_TIME_FLAG;
        }
        try {
            at = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("d-M-yyyy HHmm[ssn]"));
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException(FORMAT);
        }
    }

    static String extractNameFromCommand(String command) {
        int index = command.indexOf("/at ");
        return command.substring(6, index);
    }

    @Override
    public String toString() {
        String format = "d MMM yyyy";
        if (at.getHour() != 3 || at.getMinute() != 14 || at.getSecond() != 15 || at.getNano() != 926536) {
            if (at.getMinute() == 0) {
                format += " ha";
            } else {
                format += " h.mma";
            }
        }
        String atStr = at.format(DateTimeFormatter.ofPattern(format));
        return String.format("[%c] [%s] %s (at: %s)", 'E', getStatusIcon(), description, atStr);
    }
}