package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;

public class CommandAddToDo extends CommandAdd {

    public static final String COMMAND_STRING = "todo";

    public CommandAddToDo(TaskList taskList, Task task) {
        super(taskList, task);
    }
}
