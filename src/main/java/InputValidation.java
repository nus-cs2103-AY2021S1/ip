import java.util.Arrays;
import java.util.List;

public class InputValidation {
    static void validateCommand(String userCommand) throws DukeException {
        List<String> validCommands = Arrays.asList(
                "bye", "list", "done", "todo", "deadline", "event", "delete");

        if (!validCommands.contains(userCommand)) {
            throw new DukeException("Invalid command: " + userCommand);
        }
    }

    static void validateTask(String userCommand, String userTask) throws DukeException {
        if (userTask.isEmpty()) {
            throw new DukeException("Empty task: " + userCommand);
        }
    }

    static void validateDeadline(String userCommand, String userTask) throws DukeException {
        validateTask(userCommand, userTask);
        if (!userTask.contains("/by")) {
            throw new DukeException("Invalid deadline missing '/by': " + userTask);
        }
    }

    static void validateEvent(String userCommand, String userTask) throws DukeException {
        validateTask(userCommand, userTask);
        if (!userTask.contains("/at")) {
            throw new DukeException("Invalid event missing '/at': " + userTask);
        }
    }

    // Implement validation for identifier commands, e.g. 'done 1', 'delete 2'
    static void validateIdentifier(String userInput, String[] userTokens) throws DukeException {
        if (userTokens.length != 2) {
            throw new DukeException("Invalid identifier, requires 2 tokens: " + userInput);
        } else {
            try {
                Integer.parseInt(userTokens[1]);
            } catch (NumberFormatException nfe) {
                throw new DukeException("Invalid identifier, requires integer: " + userInput);
            }
        }
    }
}
