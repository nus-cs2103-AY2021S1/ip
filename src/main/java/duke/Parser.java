package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TerminationCommand;
import duke.command.TodoCommand;
import duke.command.UpdateCommand;
import duke.dukeexception.InvalidInputException;
import duke.dukeexception.InvalidTaskException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.parseInt;

/**
 * Makes sense of user input and parses it into appropriate <code>Command</code>s.
 *
 * @author Hui Ling
 */
public class Parser {
    /**
     * String constants for accepted commands
     */
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_UPDATE = "update";
    private static final String COMMAND_FIND = "find";
    private static final Set<String> COMMANDS_TERMINATION = new HashSet<String>(
            Arrays.asList("bye", "toodles", "farewell", "sayonara"));
    private static final Set<String> COMMANDS_NON_TERMINATION = new HashSet<String>(
            Arrays.asList(COMMAND_LIST, COMMAND_DONE, COMMAND_TODO, COMMAND_DEADLINE,
                    COMMAND_EVENT, COMMAND_DELETE, COMMAND_UPDATE, COMMAND_FIND));

    /**
     * Sole constructor.
     */
    protected Parser() {}

    /**
     * Returns the command type of a user input string by taking the first word.
     *
     * @param userInput  entire user input string as-is
     * @return a String that indicates command type
     */
    private static String getCommandType(String userInput) {
        return userInput.contains(" ")
                ? userInput.split(" ")[0]
                : userInput;  // for "list"
    }

    /**
     * Parses user input string and returns a <code>Command</code> of type corresponding to
     * the first word of the input string. Also separates all relevant fields like
     * task description, task number and date (if any) in the input string,
     * to put them into the constructor of the <code>Command</code>.
     *
     * @param userInput  entire user input string as-is
     * @return a <code>Command</code> of a certain type
     * @throws InvalidTaskException  if command type is not recognised
     * @throws InvalidInputException if user input does not have the required fields for its command type
     * @see Command
     */
    protected static Command parse(String userInput) throws InvalidTaskException, InvalidInputException {
        userInput = userInput.trim();
        String commandType = getCommandType(userInput);

        if (commandType.equals(COMMAND_LIST)) {
            // list is the only duke.command that takes only one word and nothing after a space
            return new ListCommand();
        }
        if (COMMANDS_TERMINATION.contains(commandType)) {
            return new TerminationCommand();
        }
        if (!COMMANDS_NON_TERMINATION.contains(commandType)) {
            throw new InvalidTaskException();
        }

        // valid duke.command that is not list
        if (!userInput.contains(" ")) {
            throw new InvalidInputException("Did you put your task info after a space?");
        }
        // take the part of the duke.command without commandType
        String info = userInput.split(" ", 2)[1];

        switch (commandType) {
        case COMMAND_DONE:
            try {
                int taskNumber = parseInt(info) - 1;
                return new DoneCommand(taskNumber);
            } catch (Exception e) {
                throw new InvalidInputException("Specify the task number correctly.");
            }
        case COMMAND_TODO:
            try {
                return new TodoCommand(info);
            } catch (Exception e) {
                throw new InvalidInputException("Did you put your task after a space?");
            }
        case COMMAND_EVENT:
            try {
                String[] descAndDate = info.split(" /at ");
                return new EventCommand(descAndDate[0], LocalDate.parse(descAndDate[1]));
            } catch (Exception e) {
                throw new InvalidInputException("Format for dates is yyyy-mm-dd. "
                        + "Also, did you put a task before and date after ' /at '?");
            }
        case COMMAND_DEADLINE:
            try {
                String[] descAndDate = info.split(" /by ");
                return new DeadlineCommand(descAndDate[0], LocalDate.parse(descAndDate[1]));
            } catch (Exception e) {
                throw new InvalidInputException("Format for dates is yyyy-mm-dd. "
                        + "Also, did you put a task before and deadline after ' /by '?");
            }
        case COMMAND_DELETE:
            try {
                return new DeleteCommand(parseInt(info) - 1);
            } catch (Exception e) {
                throw new InvalidInputException("Specify the task number correctly.");
            }
        case COMMAND_UPDATE:
            try {
                String[] numberAndDesc = info.split(" ", 2);
                int taskNumber = parseInt(numberAndDesc[0]) - 1;
                String newTaskDesc = numberAndDesc[1];
                return new UpdateCommand(taskNumber, newTaskDesc);
            } catch (Exception e) {
                throw new InvalidInputException("Did you put a task number and a new description "
                        + "separated by a space?");
            }
        case COMMAND_FIND:
            try {
                return new FindCommand(info);
            } catch (Exception e) {
                throw new InvalidInputException("Somehow your input is wrong.");
            }
        default:
            throw new InvalidTaskException();
        }
    }
}
