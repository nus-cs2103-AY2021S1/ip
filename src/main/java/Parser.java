import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static Command decideCategory(String input) throws IllegalArgumentException {
        String category = getCategory(input);
        String description = getDescription(input);
        switch (category) {
            case "todo":
                return parseTodoCommand(description);
            case "deadline":
                return parseDeadlineCommand(description);
            case "event":
                return parseEventCommand(description);
            case "done":
                return parseDoneCommand(description);
            case "delete":
                return parseDeleteCommand(description);
            case "list":
                return parseListCommand(description);
            case "bye":
                return parseByeCommand(description);
            default:
                throw new IllegalArgumentException(TextUi.DIVIDER +
                        "☹ OOPS!!! Invalid input. Try again!\n"
                        + TextUi.DIVIDER);
        }
    }

    public static String getCategory(String input) throws IllegalArgumentException {
        String[] wordsArray = input.split(" ", 2);
        String categoryWord = wordsArray[0];
        return categoryWord;
    }

    public static String getDescription(String input) {
        String[] wordsArray = input.split(" ", 2);
        String description = wordsArray.length == 2 ? wordsArray[1] : null;
        return description;
    }

    public static TodoCommand parseTodoCommand(String description) {
        return new TodoCommand(description);
    }

    public static DeadlineCommand parseDeadlineCommand(String description) {
        String[] descriptionArray = description.split("/by");
        String deadlineName = descriptionArray[0];

        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(" dd/MM/yyyy HH:mm");
        LocalDateTime deadlineDateTime = LocalDateTime.parse(descriptionArray[1], inputFormat);
        return new DeadlineCommand(deadlineName, deadlineDateTime);
    }

    public static EventCommand parseEventCommand(String description) {
        String[] descriptionArray = description.split("/at");
        String eventName = descriptionArray[0];

        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(" dd/MM/yyyy HH:mm");
        LocalDateTime eventDateTime = LocalDateTime.parse(descriptionArray[1], inputFormat);
        return new EventCommand(eventName, eventDateTime);
    }

    public static DoneCommand parseDoneCommand(String description) {
        return new DoneCommand(description);
    }

    public static DeleteCommand parseDeleteCommand(String description) {
        return new DeleteCommand(description);
    }

    public static ListCommand parseListCommand(String description) {
        return new ListCommand(null);
    }

    public static ByeCommand parseByeCommand(String description) {
        return new ByeCommand(null);
    }
}

