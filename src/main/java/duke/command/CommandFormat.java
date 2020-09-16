package duke.command;

public class CommandFormat {

    public static final String EXIT_CMD_FORMAT =
            "bye";

    public static final String LIST_CMD_FORMAT =
            "list";

    public static final String DEADLINE_CMD_FORMAT =
            "deadline <task description> /by <time>";

    public static final String EVENT_CMD_FORMAT =
            "event <task description> /at <time>";

    public static final String TODO_CMD_FORMAT =
            "todo <task description>";

    public static final String DONE_CMD_FORMAT =
            "done <task index>";

    public static final String DELETE_CMD_FORMAT =
            "delete <task index>";

    public static final String FIND_CMD_FORMAT =
            "find <keyword>";

    public static final String HELP_CMD_FORMAT =
            "help";

    public static final String TAG_CMD_FORMAT =
            "tag <task index> <tag>";

    public static final String UNTAG_CMD_FORMAT =
            "untag <task index>";
}
