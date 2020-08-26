package duke.command;

import duke.exception.DukeException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

public abstract class Command {
    public static String COMMAND_BYE = "bye";
    public static String COMMAND_LIST = "list";
    public static String COMMAND_DONE = "done";
    public static String COMMAND_DELETE = "delete";
    public static String COMMAND_TODO = "todo";
    public static String COMMAND_DEADLINE = "deadline";
    public static String COMMAND_EVENT = "event";
    public static String COMMAND_TASK_AFTER = "taskafter";
    public static String COMMAND_TASK_BEFORE = "taskbefore";

    private boolean isExit;

    Command() {
        this.isExit = false;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void exit() {
        this.isExit = true;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
