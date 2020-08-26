import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Parser {

    /** Parse a user input string as a delete command.
     * @param userInput - String representation of user input
     * @return integer index of the task in the task list to be deleted
     * */
    static int parseDelete(String userInput) throws DukeException {
        String[] userTokens = userInput.split(" ");
        Parser.validateIdentifier(userInput, userTokens);
        return Integer.parseInt(userTokens[1]) - 1;
    }

    /** Parse a user input string as a done command.
     * @param userInput - String representation of user input
     * @return integer index of the task in the task list to be marked as done
     * */
    static int parseDone(String userInput) throws DukeException {
        String[] userTokens = userInput.split(" ");
        Parser.validateIdentifier(userInput, userTokens);
        return Integer.parseInt(userTokens[1]) - 1;
    }

    /** Parse a user input string as a event command.
     * @param userInput - String representation of user input
     * @return Event task created from user command
     * */
    static Event parseEvent(String userInput) throws DukeException {
        String[] userTokens = userInput.split(" ");
        String userCommand = userTokens[0];
        String userTask = String.join(" ", Arrays.copyOfRange(userTokens, 1, userTokens.length));
        Parser.validateEvent(userCommand, userTask);
        String[] taskTokens = userTask.split(" /at ");
        String taskName = taskTokens[0];
        LocalDate taskAt = LocalDate.parse(taskTokens[1]);
        return new Event(taskName, taskAt);
    }

    /** Parse a user input string as a deadline command.
     * @param userInput - String representation of user input
     * @return Deadline task created from user command
     * */
    static Deadline parseDeadline(String userInput) throws DukeException {
        String[] userTokens = userInput.split(" ");
        String userCommand = userTokens[0];
        String userTask = String.join(" ", Arrays.copyOfRange(userTokens, 1, userTokens.length));
        Parser.validateDeadline(userCommand, userTask);
        String[] taskTokens = userTask.split(" /by ");
        String taskName = taskTokens[0];
        LocalDate taskBy = LocalDate.parse(taskTokens[1]);
        return new Deadline(taskName, taskBy);
    }

    /** Parse a user input string as a todo command.
     * @param userInput - String representation of user input
     * @return Todo task created from user command
     * */
    static Todo parseTodo(String userInput) throws DukeException {
        String[] userTokens = userInput.split(" ");
        String userCommand = userTokens[0];
        String userTask = String.join(" ", Arrays.copyOfRange(userTokens, 1, userTokens.length));
        Parser.validateTask(userCommand, userTask);
        return new Todo(userTask);
    }

    /** Validate a command from the user and throws an exception if invalid.
     * @param userCommand - String representation of user's command
     * */
    static void validateCommand(String userCommand) throws DukeException {
        List<String> validCommands = Arrays.asList(
                "bye", "list", "done", "todo", "deadline", "event", "delete");

        if (!validCommands.contains(userCommand)) {
            throw new DukeException("Invalid command: " + userCommand);
        }
    }

    /** Validate a task from the user and throws an exception if invalid.
     * @param userCommand - String representation of user's command
     * @param userTask - String representation of user's task
     * */
    static void validateTask(String userCommand, String userTask) throws DukeException {
        if (userTask.isEmpty()) {
            throw new DukeException("Empty task: " + userCommand);
        }
    }

    /** Validate a deadline from the user and throws an exception if invalid.
     * @param userCommand - String representation of user's command
     * @param userTask - String representation of user's task
     * */
    static void validateDeadline(String userCommand, String userTask) throws DukeException {
        validateTask(userCommand, userTask);
        if (!userTask.contains("/by")) {
            throw new DukeException("Invalid deadline missing '/by': " + userTask);
        }
    }

    /** Validate a event from the user and throws an exception if invalid.
     * @param userCommand - String representation of user's command
     * @param userTask - String representation of user's task
     * */
    static void validateEvent(String userCommand, String userTask) throws DukeException {
        validateTask(userCommand, userTask);
        if (!userTask.contains("/at")) {
            throw new DukeException("Invalid event missing '/at': " + userTask);
        }
    }

    // TODO: Implement validation for identifier commands, e.g. 'done 1', 'delete 2'
    /** Validate an identifier from the user and throws an exception if invalid.
     * @param userInput - String representation of user's input
     * @param userTokens - String array of user's input separated by spaces
     * */
    static void validateIdentifier(String userInput, String[] userTokens) throws DukeException {
        if (userTokens.length != 2) {
            throw new DukeException("Invalid identifier, requires 2 tokens: " + userInput);
        }
        try {
            Integer.parseInt(userTokens[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid identifier, requires integer: " + userInput);
        }
    }
}
