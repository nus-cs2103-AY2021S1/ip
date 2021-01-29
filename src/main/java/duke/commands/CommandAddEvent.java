package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;

public class CommandAddEvent extends CommandAdd {

    public static final String COMMAND_STRING = "event";

    public CommandAddEvent(TaskList taskList, Task task) {
        super(taskList, task);
    }

}
