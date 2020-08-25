import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    //error messages
    private static final String MESSAGE_MISSING_DATETIME = "Did you casually forget to put in the date/time?";
    private static final String MESSAGE_MISSING_DELIM = "You didn't put the correct delimiter in the command";
    private static final String MESSAGE_INVALID = "I don't understand anything you just said";

    private static final HashMap<String, TaskType> taskTypeMap = new HashMap<>(Map.of(
            "event", TaskType.E,
            "deadline", TaskType.D,
            "todo", TaskType.T));

    public static Command parse(String input)
            throws MissingDelimiterException, MissingDateTimeException, InvalidCommandException,
            DateTimeParseException, IndexOutOfBoundsException {
        String[] commandElements = input.split(" ");
        Command intendedCommand;
        switch (commandElements[0]) {
        case "bye":
            intendedCommand = new ExitCommand();
            break;
        case "list":
            intendedCommand = new ListCommand();
            break;
        case "done":
            short taskId;
            taskId = Short.parseShort(commandElements[1]);
            intendedCommand = new DoneCommand(taskId);
            break;
        case "delete":
            taskId = Short.parseShort(commandElements[1]);
            intendedCommand = new DeleteCommand(taskId);
            break;
        case "find":
            intendedCommand = new FindCommand(String.join(" ",
                    Arrays.copyOfRange(commandElements, 1, commandElements.length)));
            break;
        default:
            TaskType currType = taskTypeMap.get(commandElements[0]);
            if (currType == null) {
                throw new InvalidCommandException(MESSAGE_INVALID);
            }
            switch (currType) {
            case D:
                int delimiter = Arrays.asList(commandElements).indexOf("/by");
                if (delimiter < 0) {
                    throw new MissingDelimiterException(MESSAGE_MISSING_DELIM);
                }
                if (delimiter + 3 > commandElements.length) {
                    throw new MissingDateTimeException(MESSAGE_MISSING_DATETIME);
                }
                intendedCommand = new AddCommand(currType,
                        String.join(" ", Arrays.copyOfRange(commandElements, 1, delimiter)),
                        LocalDate.parse(commandElements[delimiter + 1], DateTimeFormatter.ofPattern("dd-MM-yy")),
                        LocalTime.parse(commandElements[delimiter + 2], DateTimeFormatter.ofPattern("HH:mm")));
                break;
            case E:
                delimiter = Arrays.asList(commandElements).indexOf("/at");
                if (delimiter < 0) {
                    throw new MissingDelimiterException(MESSAGE_MISSING_DELIM);
                }
                if (delimiter + 3 > commandElements.length) {
                    throw new MissingDateTimeException(MESSAGE_MISSING_DATETIME);
                }
                intendedCommand = new AddCommand(currType,
                        String.join(" ", Arrays.copyOfRange(commandElements, 1, delimiter)),
                        LocalDate.parse(commandElements[delimiter + 1], DateTimeFormatter.ofPattern("dd-MM-yy")),
                        LocalTime.parse(commandElements[delimiter + 2], DateTimeFormatter.ofPattern("HH:mm")));
                break;
            default:
                intendedCommand = new AddCommand(currType,
                        String.join(" ", Arrays.copyOfRange(commandElements, 1, commandElements.length)),
                        null,
                        null);
                break;
            }
            break;
        }
        return intendedCommand;
    }
}
