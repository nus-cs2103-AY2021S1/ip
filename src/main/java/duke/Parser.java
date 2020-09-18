package duke;

import duke.Ui;
import duke.command.FindCommand;
import duke.command.Command;
import duke.command.ListCommand;
import duke.command.DoneCommand;
import duke.command.TodoCommand;
import duke.command.EventCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.UpdateCommand;
import duke.command.TerminationCommand;
import duke.dukeexception.InvalidInputException;
import duke.dukeexception.InvalidTaskException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class Parser {
    static final String COMMAND_LIST = "list";
    static final String COMMAND_DONE = "done";
    static final String COMMAND_TODO = "todo";
    static final String COMMAND_DEADLINE = "deadline";
    static final String COMMAND_EVENT = "event";
    static final String COMMAND_DELETE = "delete";
    static final String COMMAND_UPDATE = "update";
    static final String COMMAND_FIND = "find";
    static final Set<String> terminationCommands = new HashSet<String>(
            Arrays.asList("bye", "toodles", "farewell", "sayonara"));
    static final Set<String> nonTerminationCommands = new HashSet<String>(
            Arrays.asList(COMMAND_LIST, COMMAND_DONE, COMMAND_TODO, COMMAND_DEADLINE,
                    COMMAND_EVENT, COMMAND_DELETE, COMMAND_UPDATE, COMMAND_FIND));

    public Parser() {}

    private static String getCommandType(String fullCommand) {
        return fullCommand.contains(" ")
                ? fullCommand.split(" ")[0]
                : fullCommand;  // list
    }

    public static Command parse(String fullCommand) throws InvalidTaskException, InvalidInputException {
        fullCommand = fullCommand.trim();
        String commandType = getCommandType(fullCommand);

        if (commandType.equals(COMMAND_LIST)) {
            // list is the only duke.command that takes only one word and nothing after a space
            return new ListCommand();
        }
        if (terminationCommands.contains(commandType)) {
            return new TerminationCommand();
        }
        if (!nonTerminationCommands.contains(commandType)) {
            throw new InvalidTaskException();
        }

        // valid duke.command that is not list
        if (!fullCommand.contains(" ")) {
            throw new InvalidInputException("Did you put your task info after a space?");
        }
        // take the part of the duke.command without commandType
        String info = fullCommand.split(" ", 2)[1];

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
                throw new InvalidInputException("Format for dates is yyyy-mm-dd. " +
                        "Also, did you put a task before and date after ' /at '?");
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
