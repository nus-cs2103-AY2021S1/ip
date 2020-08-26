public class Parser {
    private static final String CMD_EXIT = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_DONE = "done";
    private static final String CMD_DELETE = "delete";
    private static final String CMD_DUE = "due";
    private static final String CMD_DEADLINE = "deadline";
    private static final String CMD_TODO = "todo";
    private static final String CMD_EVENT = "event";

    public static CommandType parseCmdWord(String in) {
        String[] input = in.split(" ");
        String cmdWord = input[0];
        switch (cmdWord) {
        case CMD_EXIT:
            return CommandType.Exit;
        case CMD_LIST:
            return CommandType.List;
        case CMD_DONE:
            return CommandType.Done;
        case CMD_DELETE:
            return CommandType.Delete;
        case CMD_DUE:
            return CommandType.Due;
        case CMD_DEADLINE:
            // fallthrough
        case CMD_EVENT:
            //fallthrough
        case CMD_TODO:
            return CommandType.Task;
        default:
            return CommandType.Invalid;
        }
    }

    public static TaskType parseTaskType(String in) throws InvalidCommandException {
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
