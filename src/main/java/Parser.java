import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the driver that parses the commands provided by the client.
 */
public class Parser {
    //error messages
    private static final String MESSAGE_MISSING_DATETIME = "Did you casually forget to put in the date/time?";
    private static final String MESSAGE_MISSING_SLASH = "You didn't put the correct delimiter in the command";
    private static final String MESSAGE_INVALID = "I don't understand anything you just said";

    private static final HashMap<String, TaskType> TASK_TYPE_MAP = new HashMap<>(Map.of(
            "event", TaskType.E,
            "deadline", TaskType.D,
            "todo", TaskType.T));

    private static final String[] RESTRICTED_WORDS =
            { "addalias", "bye", "deadline", "delete", "done", "event", "find", "list", "todo" };
    private static final String MESSAGE_ALIAS_NOT_ALLOWED = "This mapping is not allowed!";

    private final HashMap<String, String> aliasMap;

    public Parser(HashMap<String, String> aliasMap) {
        this.aliasMap = aliasMap;
    }

    /**
     * Parses the commands provided by the client.
     *
     * @param input Input Command.
     * @return Parsed Command.
     * @throws MissingDelimiterException If a delimiter for date/time is required but missing.
     * @throws MissingDateTimeException  If a date/time is required but missing.
     * @throws InvalidCommandException   If the command provided is not recognised.
     * @throws DateTimeParseException    If the date/time is in the wrong format.
     * @throws IndexOutOfBoundsException If the task number provided is invalid.
     */
    public Command parse(String input)
            throws MissingDelimiterException, MissingDateTimeException, InvalidCommandException,
            DateTimeParseException, IndexOutOfBoundsException, AliasNotAllowedException,NumberFormatException {
        String[] commandElements = input.trim().split(" ");

        // replaces custom mapping with default commands
        if (aliasMap.containsKey(commandElements[0])) {
            commandElements[0] = aliasMap.get(commandElements[0]);
        }

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
        case "addalias":
            if (Arrays.binarySearch(RESTRICTED_WORDS, commandElements[1]) >= 0
                    || Arrays.binarySearch(RESTRICTED_WORDS, commandElements[2]) < 0) {
                throw new AliasNotAllowedException(MESSAGE_ALIAS_NOT_ALLOWED);
            }
            aliasMap.put(commandElements[1], commandElements[2]);
            intendedCommand = new AddAliasCommand(aliasMap, commandElements[1]);
            break;
        default:
            intendedCommand = parseTask(commandElements);
            break;
        }
        return intendedCommand;
    }

    private static Command parseTask(String[] commandElements)
            throws InvalidCommandException, MissingDelimiterException, MissingDateTimeException {
        Command intendedCommand;
        TaskType currType = TASK_TYPE_MAP.get(commandElements[0]);

        if (currType == null) {
            throw new InvalidCommandException(MESSAGE_INVALID);
        }

        switch (currType) {
        case D:
            int delimiter = Arrays.asList(commandElements).indexOf("/by");
            if (delimiter < 0) {
                throw new MissingDelimiterException(MESSAGE_MISSING_SLASH);
            }
            if (delimiter + 3 > commandElements.length) {
                throw new MissingDateTimeException(MESSAGE_MISSING_DATETIME);
            }
            intendedCommand = new AddTaskCommand(currType,
                    String.join(" ", Arrays.copyOfRange(commandElements, 1, delimiter)),
                    LocalDate.parse(commandElements[delimiter + 1], DateTimeFormatter.ofPattern("dd-MM-yy")),
                    LocalTime.parse(commandElements[delimiter + 2], DateTimeFormatter.ofPattern("HH:mm")));
            break;
        case E:
            delimiter = Arrays.asList(commandElements).indexOf("/at");
            if (delimiter < 0) {
                throw new MissingDelimiterException(MESSAGE_MISSING_SLASH);
            }
            if (delimiter + 3 > commandElements.length) {
                throw new MissingDateTimeException(MESSAGE_MISSING_DATETIME);
            }
            intendedCommand = new AddTaskCommand(currType,
                    String.join(" ", Arrays.copyOfRange(commandElements, 1, delimiter)),
                    LocalDate.parse(commandElements[delimiter + 1], DateTimeFormatter.ofPattern("dd-MM-yy")),
                    LocalTime.parse(commandElements[delimiter + 2], DateTimeFormatter.ofPattern("HH:mm")));
            break;
        case T:
            intendedCommand = new AddTaskCommand(currType,
                    String.join(" ", Arrays.copyOfRange(commandElements, 1, commandElements.length)),
                    null,
                    null);
            break;
        default:
            throw new InvalidCommandException(MESSAGE_INVALID);
        }
        return intendedCommand;
    }
}
