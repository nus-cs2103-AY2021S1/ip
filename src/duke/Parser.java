package duke;

import command.*;

public class Parser {
    private final static String EXIT_COMMAND = "bye";
    private final static String MARK_DONE_COMMAND = "done";
    private final static String DISPLAY_TASKS_COMMAND = "list";
    private final static String TODO_COMMAND = "todo";
    private final static String DEADLINE_COMMAND = "deadline";
    private final static String EVENT_COMMAND = "event";
    private final static String DELETE_COMMAND = "delete";

    public static Command parse(String fullCommand) {
        String[] splitCommand = fullCommand.split(" ", 2);
        switch (splitCommand[0]) {
            case MARK_DONE_COMMAND:
                return new MarkDoneCommand(splitCommand);
            case TODO_COMMAND:
                return new AddToDoCommand(splitCommand);
            case DEADLINE_COMMAND:
                return new AddDeadlineCommand(splitCommand);
            case EVENT_COMMAND:
                return new AddEventCommand(splitCommand);
            case DELETE_COMMAND:
                return new DeleteCommand(splitCommand);
            case DISPLAY_TASKS_COMMAND:
                return new TaskListCommand(splitCommand);
            case EXIT_COMMAND:
                return new ExitCommand(splitCommand);
            default:
                return new UnknownCommand(splitCommand);
        }
    }
}
