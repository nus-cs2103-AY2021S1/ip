package duke;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;



/**
 * Parser deals with making sense of the user command.
 */
public class Parser {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_TAG = "tag";

    public static Command parse(String input) throws DukeException {
        if (input.length() == 0) {
            throw new InvalidInputException();
        }
        String[] inputArr = input.split(" ", 2);
        String commandType = inputArr[0];
        String commandContent;
        if (commandType.equals(COMMAND_BYE)) {
            return new ByeCommand();
        } else if (commandType.equals(COMMAND_LIST)) {
            return new ListCommand();
        }
        assert inputArr.length == 2 : "No Available Condition for Operation!";
        
        commandContent = inputArr[1];
        if (commandType.equals(COMMAND_DONE)) {
            return new DoneCommand(commandContent);
        } else if (commandType.equals(COMMAND_FIND)) {
            return new FindCommand(commandContent);
        } else if (commandType.equals(COMMAND_DELETE)) {
            return new DeleteCommand(commandContent);
        } else if (commandType.equals(COMMAND_TODO)) {
            return new AddToDoCommand(commandContent);
        } else if (commandType.equals(COMMAND_EVENT)) {
            return new AddEventCommand(commandContent);
        } else if (commandType.equals(COMMAND_DEADLINE)) {
            return new AddDeadlineCommand(commandContent);
        } else if (commandType.equals(COMMAND_TAG)) {
            String[] tagInfo = commandContent.split(" ", 2);
            int taskIndex = Integer.parseInt(tagInfo[0]);
            String taskTag = tagInfo[1];
            return new TagCommand(taskIndex, taskTag);
        }
        return new AddCommand(input);
    }
}
