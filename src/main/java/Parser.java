import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * handles the parsing of information, from user input to different commands
 */
public class Parser {
    /**
     * parses the user input into commands based on the type of tasks
     *
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
        case "find":
            return parseFindCommand(description);
        case "fixed":
            return parseFixedDurationTaskCommand(description);
        default:
            throw new IllegalArgumentException("Invalid input.");
        }
    }

    /**
     * getter for the category/ type of the task
     *
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
     *
     * @param input
     * @return the task description
     */

    public static String getDescription(String input) {
        String[] wordsArray = input.split(" ", 2);
        String description = wordsArray.length == 2 ? wordsArray[1] : "";
        return description;
    }

    /**
     * parse user input into TodoCommand
     *
     * @param description
     * @return TodoCommand
     */
    public static TodoCommand parseTodoCommand(String description) {
        if (description.equals("")) {
            throw new IllegalArgumentException("The description of a todo cannot be empty.");
        } else {
            return new TodoCommand(description);
        }
    }

    /**
     * parse user input into DeadlineCommand
     *
     * @param description
     * @return DeadlineCommand
     */
    public static DeadlineCommand parseDeadlineCommand(String description) {
        String[] descriptionArray = description.split("/by");
        String deadlineName = descriptionArray[0];
        if (deadlineName == null) {
            throw new IllegalArgumentException("The description of a deadline cannot be empty. ");
        } else if (descriptionArray.length == 1) { //no "/at" present
            throw new IllegalArgumentException("Invalid input, no deadline stated. ");
        } else if (descriptionArray.length > 2) {
            throw new IllegalArgumentException("Invalid input, multiple deadlines stated. ");
        } else {
            try {
                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(" dd/MM/yyyy HH:mm");
                LocalDateTime deadlineDateTime = LocalDateTime.parse(descriptionArray[1], inputFormat);
                return new DeadlineCommand(deadlineName, deadlineDateTime);
            } catch (DateTimeParseException exception) {
                throw new IllegalArgumentException("Incorrect formatting - write it in dd/MM/yyyy HH:mm format");
            }
        }
    }

    /**
     * parse user input into EventCommand
     *
     * @param description
     * @return EventCommand
     */
    public static EventCommand parseEventCommand(String description) {
        String[] descriptionArray = description.split("/at");
        String eventName = descriptionArray[0];
        if (eventName == null) {
            throw new IllegalArgumentException("The description of an event cannot be empty. ");
        } else if (descriptionArray.length == 1) { //no "/at" present
            throw new IllegalArgumentException("Invalid input, no event time stated. ");
        } else if (descriptionArray.length > 2) {
            throw new IllegalArgumentException("Invalid input, multiple deadlines stated. ");
        } else {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(" dd/MM/yyyy HH:mm");
            LocalDateTime eventDateTime = LocalDateTime.parse(descriptionArray[1], inputFormat);
            return new EventCommand(eventName, eventDateTime);
        }
    }

    /**
     * parse user input into DoneCommand
     *
     * @param description
     * @return DoneCommand
     */
    public static DoneCommand parseDoneCommand(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Not sure which task is to be indicated as done. ");
        } else {
            return new DoneCommand(description);
        }
    }

    /**
     * parse user input into DeleteCommand
     *
     * @param description
     * @return DeleteCommand
     */
    public static DeleteCommand parseDeleteCommand(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Not sure which task is to be deleted. ");
        } else {
            return new DeleteCommand(description);
        }
    }

    /**
     * parse user input into ListCommand
     *
     * @param description
     * @return ListCommand
     */
    public static ListCommand parseListCommand(String description) {
        if (!description.equals("")) {
            throw new IllegalArgumentException(" Invalid input. ");
        } else {
            return new ListCommand(null);
        }
    }

    /**
     * parse user input into ListCommand
     *
     * @param description
     * @return ByeCommand
     */
    public static ByeCommand parseByeCommand(String description) {
        if (!description.equals("")) {
            throw new IllegalArgumentException(" Invalid input. ");
        } else {
            return new ByeCommand(null);
        }
    }

    /**
     * parse user input into FindCommand
     *
     * @param description
     * @return FindCommand
     */
    public static FindCommand parseFindCommand(String description) {
        if (description == null) {
            throw new IllegalArgumentException("No keyword found. ");
        } else {
            return new FindCommand(description);
        }
    }
    /**
     * parse user input into FixedDurationTaskCommand
     *
     * @param description
     * @return FixedDurationTaskCommand
     */
    public static FixedDurationTaskCommand parseFixedDurationTaskCommand(String description) {
        String[] descriptionArray = description.split("/for");
        String taskName = descriptionArray[0];
        if (taskName == null) {
            throw new IllegalArgumentException("The description of a task cannot be empty. ");
        } else if (descriptionArray.length == 1) {
            throw new IllegalArgumentException("Invalid input, no duration stated. ");
        } else if (descriptionArray.length > 2) {
            throw new IllegalArgumentException("Invalid input, multiple durations stated. ");
        } else {
            return new FixedDurationTaskCommand(taskName, descriptionArray[1]);
        }
    }
}


