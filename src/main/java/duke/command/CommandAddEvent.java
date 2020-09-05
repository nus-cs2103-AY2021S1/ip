package duke.command;

import duke.Task;
import duke.TaskList;
import duke.ui.Ui;

public class CommandAddEvent extends CommandAdd {

    public static final String COMMAND_STRING = "event";

    public CommandAddEvent(TaskList taskList, Ui ui, Task task) {
        super(taskList, ui, task);
    }
}
