package duke;

import duke.commands.AddCommand;
import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddToDoCommand;
import duke.commands.ByeCommand;
import duke.commands.ListCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.DeleteCommand;
import duke.commands.Command;
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
    private static final String COMMAND_DEADLINE = "dealine";
    private static final String COMMAND_EVENT = "event";

    public static boolean isToDo(String commandType) {
        return commandType.equals(COMMAND_TODO);
    }

    public static boolean isDeadline(String commandType) {
        return commandType.equals(COMMAND_DEADLINE);
    }

    public static boolean isEvent(String commandType) {
        return commandType.equals(COMMAND_EVENT);
    }

    public static Command parse(String input) throws DukeException {
        if (input.length() == 0) {
            throw new InvalidInputException();
        }
        String[] inputArr = input.split(" ", 2);
        String commandType = inputArr[0];
        String commandContent;
        if (commandType.equals(COMMAND_BYE)) {
            return new ByeCommand();
        } else if (commandType.equals(COMMAND_DONE)) {
            commandContent = inputArr[1];
            return new DoneCommand(commandContent);
        } else if (commandType.equals(COMMAND_LIST)) {
            return new ListCommand();
        } else if (commandType.equals(COMMAND_FIND)) {
            commandContent = inputArr[1];
            return new FindCommand(commandContent);
        } else if (commandType.equals(COMMAND_DELETE)) {
            commandContent = inputArr[1];
            return new DeleteCommand(commandContent);
        } else if (commandType.equals(COMMAND_TODO)){
            commandContent = inputArr[1];
            return new AddToDoCommand(commandContent);
        } else if (commandType.equals(COMMAND_EVENT)) {
            commandContent = inputArr[1];
            return new AddEventCommand(commandContent);
        } else if (commandType.equals(COMMAND_DEADLINE)) {
            commandContent = inputArr[1];
            return new AddDeadlineCommand(commandContent);
        } else {
            return new AddCommand(input);
        }
    }
}