package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class CommandAddDeadline extends CommandAdd {

    public static final String COMMAND_STRING = "deadline";

    public CommandAddDeadline(TaskList taskList, Ui ui, Task task) {
        super(taskList, ui, task);
    }
}
