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
        String[] inputArr = input.split(" ", 1);
        String commandType = inputArr[0];
        String commandContent = inputArr[1];
        try {
            if (commandType.equals(COMMAND_BYE)) {
                return new ByeCommand();
            } else if (commandType.equals(COMMAND_DONE)) {

            } else if (commandType.equals(COMMAND_LIST)) {
                return new ListCommand();
            } else if (commandType.equals(COMMAND_FIND)) {

            } else if (commandType.equals(COMMAND_DELETE)) {

            } else if (commandType.equals(COMMAND_TODO)){
                return new AddToDoCommand(commandContent);
            } else if (commandType.equals(COMMAND_EVENT)) {
                return new AddEventCommand(commandContent);
            } else if (commandType.equals(COMMAND_DEADLINE)) {
                return new AddDeadlineCommand(commandContent);
            } else {
                return new AddCommand(input);
            }
        } catch (Exception e) {
            throw new DukeException("Sorry! No available command for your input! Please try again!");
        }
    }
}