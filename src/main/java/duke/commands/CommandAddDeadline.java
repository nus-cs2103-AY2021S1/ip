package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;

public class CommandAddDeadline extends CommandAdd {

    public static final String COMMAND_STRING = "deadline";

    public CommandAddDeadline(TaskList taskList, Task task) {
        super(taskList, task);
    }
}
