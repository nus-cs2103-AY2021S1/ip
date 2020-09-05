package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class CommandAdd extends Command {

    private Task task;

    public static final String COMMAND_STRING = "add";

    private static final String ADD_TASK_CONFIRMATION = "Fine. I've added this task:\n" + "%s\n"
            + "Now you have a total of %d %s in your list.\n";

    public CommandAdd(TaskList taskList, Ui ui, Task task) {
        super(taskList, ui);
        this.task = task;
    }

    @Override
    public void execute() {
        taskList.add(task);
        int size = taskList.getSize();
        ui.outputBlockToUser(String.format(ADD_TASK_CONFIRMATION, task, size, (size == 1 ? "task" : "tasks")));
    }

    public Task getTask() {
        return task;
    }
}
