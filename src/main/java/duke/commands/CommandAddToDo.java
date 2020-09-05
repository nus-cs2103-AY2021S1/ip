package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class CommandAddToDo extends CommandAdd {

    public static final String COMMAND_STRING = "todo";

    public CommandAddToDo(TaskList taskList, Ui ui, Task task) {
        super(taskList, ui, task);
    }
}
