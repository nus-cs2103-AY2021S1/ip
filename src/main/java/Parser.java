import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * handles the parsing of information, from user input to different commands
 */
public class Parser {
    /**
     * parses the user input into commands based on the type of tasks
     * @param input
     * @return the command
     * @throws IllegalArgumentException
     */
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

    /**
     * getter for the category/ type of the task
     * @param input
     * @return the type of the task
     * @throws IllegalArgumentException
     */
    public static String getCategory(String input) throws IllegalArgumentException {
        String[] wordsArray = input.split(" ", 2);
        String categoryWord = wordsArray[0];
        return categoryWord;
    }

    /**
     * getter for the description of the task
     * @param input
     * @return the task description
     */

    public static String getDescription(String input) {
        String[] wordsArray = input.split(" ", 2);
        String description = wordsArray.length == 2 ? wordsArray[1] : null;
        return description;
    }

    /**
     * parse user input into TodoCommand
     * @param description
     * @return TodoCommand
     */
    public static TodoCommand parseTodoCommand(String description) {
        return new TodoCommand(description);
    }

    /**
     * parse user input into DeadlineCommand
     * @param description
     * @return DeadlineCommand
     */
    public static DeadlineCommand parseDeadlineCommand(String description) {
        String[] descriptionArray = description.split("/by");
        String deadlineName = descriptionArray[0];

        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(" dd/MM/yyyy HH:mm");
        LocalDateTime deadlineDateTime = LocalDateTime.parse(descriptionArray[1], inputFormat);
        return new DeadlineCommand(deadlineName, deadlineDateTime);
    }

    /**
     * parse user input into EventCommand
     * @param description
     * @return EventCommand
     */
    public static EventCommand parseEventCommand(String description) {
        String[] descriptionArray = description.split("/at");
        String eventName = descriptionArray[0];

        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(" dd/MM/yyyy HH:mm");
        LocalDateTime eventDateTime = LocalDateTime.parse(descriptionArray[1], inputFormat);
        return new EventCommand(eventName, eventDateTime);
    }

    /**
     * parse user input into DoneCommand
     * @param description
     * @return DoneCommand
     */
    public static DoneCommand parseDoneCommand(String description) {
        return new DoneCommand(description);
    }

    /**
     * parse user input into DeleteCommand
     * @param description
     * @return DeleteCommand
     */
    public static DeleteCommand parseDeleteCommand(String description) {
        return new DeleteCommand(description);
    }

    /**
     * parse user input into ListCommand
     * @param description
     * @return ListCommand
     */
    public static ListCommand parseListCommand(String description) {
        return new ListCommand(null);
    }

    /**
     * parse user input into ListCommand
     * @param description
     * @return ListCommand
     */
    public static ByeCommand parseByeCommand(String description) {
        return new ByeCommand(null);
    }
}

