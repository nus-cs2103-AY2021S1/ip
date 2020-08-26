import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Parser {

    static int parseDelete(String userInput) throws DukeException {
        String[] userTokens = userInput.split(" ");
        String userCommand = userTokens[0];
        String userTask = String.join(" ", Arrays.copyOfRange(userTokens, 1, userTokens.length));

        Parser.validateIdentifier(userInput, userTokens);
        return Integer.parseInt(userTokens[1]) - 1;
    }

    static int parseDone(String userInput) throws DukeException {
        String[] userTokens = userInput.split(" ");
        String userCommand = userTokens[0];
        String userTask = String.join(" ", Arrays.copyOfRange(userTokens, 1, userTokens.length));

        // Validate done
        Parser.validateIdentifier(userInput, userTokens);
        return Integer.parseInt(userTokens[1]) - 1;
    }

    static Event parseEvent(String userInput) throws DukeException {
        String[] userTokens = userInput.split(" ");
        String userCommand = userTokens[0];
        String userTask = String.join(" ", Arrays.copyOfRange(userTokens, 1, userTokens.length));

        // Validate event
        Parser.validateEvent(userCommand, userTask);
        String[] taskTokens = userTask.split(" /at ");
        String taskName = taskTokens[0];
        LocalDate taskAt = LocalDate.parse(taskTokens[1]);
        return new Event(taskName, taskAt);
    }

    static Deadline parseDeadline(String userInput) throws DukeException {
        String[] userTokens = userInput.split(" ");
        String userCommand = userTokens[0];
        String userTask = String.join(" ", Arrays.copyOfRange(userTokens, 1, userTokens.length));

        // Validate deadline
        Parser.validateDeadline(userCommand, userTask);
        String[] taskTokens = userTask.split(" /by ");
        String taskName = taskTokens[0];
        LocalDate taskBy = LocalDate.parse(taskTokens[1]);
        return new Deadline(taskName, taskBy);
    }

    static Todo parseTodo(String userInput) throws DukeException {
        String[] userTokens = userInput.split(" ");
        String userCommand = userTokens[0];
        String userTask = String.join(" ", Arrays.copyOfRange(userTokens, 1, userTokens.length));

        // Validate task
        Parser.validateTask(userCommand, userTask);
        return new Todo(userTask);
    }

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
        }
        try {
            Integer.parseInt(userTokens[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid identifier, requires integer: " + userInput);
        }
    }
}
