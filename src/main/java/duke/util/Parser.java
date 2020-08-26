package duke.util;

import duke.command.CommandType;
import duke.task.TaskType;

// Parses and categorises the user's command.
public class Parser {
    private static final String CMD_DEADLINE = "deadline";
    private static final String CMD_DELETE = "delete";
    private static final String CMD_DONE = "done";
    private static final String CMD_DUE = "due";
    private static final String CMD_EVENT = "event";
    private static final String CMD_EXIT = "bye";
    private static final String CMD_FIND = "find";
    private static final String CMD_LIST = "list";
    private static final String CMD_TODO = "todo";

    /**
     * Parses the user's command and return the type of command issued by the user.
     *
     * @param in String command provided by user
     * @return CommandType type of command
     */
    public static CommandType parseCmdWord(String in) {
        String[] input = in.split(" ");
        String cmdWord = input[0];
        switch (cmdWord) {
        case CMD_DELETE:
            return CommandType.Delete;
        case CMD_DONE:
            return CommandType.Done;
        case CMD_DUE:
            return CommandType.Due;
        case CMD_EXIT:
            return CommandType.Exit;
        case CMD_FIND:
            return CommandType.Find;
        case CMD_LIST:
            return CommandType.List;
        case CMD_DEADLINE:
            // fallthrough
        case CMD_EVENT:
            // fallthrough
        case CMD_TODO:
            return CommandType.Task;
        default:
            return CommandType.Invalid;
        }
    }

    /**
     * Parses the user's "task" command and return the type of "task" command issued by the user.
     *
     * @param in String "task" command provided by user
     * @return CommandType type of "task" command
     */
    public static TaskType parseTaskType(String in) {
        String[] input = in.split(" ");
        String task = input[0];
        switch (task) {
        case "deadline":
            return TaskType.Deadline;
        case "event":
            return TaskType.Event;
        case "todo": // todo task
            return TaskType.Todo;
        default:
            return TaskType.Invalid;
        }
    }
}
