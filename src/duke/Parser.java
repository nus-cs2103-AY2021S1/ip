package duke;

import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddToDoCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.MarkDoneCommand;
import command.TaskListCommand;
import command.UnknownCommand;

public class Parser {
    private final static String COMMAND_EXIT = "bye";
    private final static String COMMAND_MARK_DONE = "done";
    private final static String COMMAND_DISPLAY_TASKS = "list";
    private final static String COMMAND_ADD_TODO = "todo";
    private final static String COMMAND_ADD_DEADLINE = "deadline";
    private final static String COMMAND_ADD_EVENT = "event";
    private final static String COMMAND_DELETE_TASK = "delete";

    public static Command parse(String fullCommand) {
        String[] splitCommand = fullCommand.split(" ", 2);
        switch (splitCommand[0]) {
        case COMMAND_MARK_DONE:
            return new MarkDoneCommand(splitCommand);
        case COMMAND_ADD_TODO:
            return new AddToDoCommand(splitCommand);
        case COMMAND_ADD_DEADLINE:
            return new AddDeadlineCommand(splitCommand);
        case COMMAND_ADD_EVENT:
            return new AddEventCommand(splitCommand);
        case COMMAND_DELETE_TASK:
            return new DeleteCommand(splitCommand);
        case COMMAND_DISPLAY_TASKS:
            return new TaskListCommand(splitCommand);
        case COMMAND_EXIT:
            return new ExitCommand(splitCommand);
        default:
            return new UnknownCommand(splitCommand);
        }
    }
}
