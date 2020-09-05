package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class CommandAddEvent extends CommandAdd {

    public static final String COMMAND_STRING = "event";

    public CommandAddEvent(TaskList taskList, Ui ui, Task task) {
        super(taskList, ui, task);
    }

}
