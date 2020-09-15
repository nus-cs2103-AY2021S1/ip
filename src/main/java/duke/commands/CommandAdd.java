package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;

public class CommandAdd extends Command {

    public static final String COMMAND_STRING = "add";

    private static final String ADD_TASK_CONFIRMATION = "Fine. I've added this task:\n" + "%s\n"
            + "Now you have a total of %d %s in your list.\n";

    private Task task;

    /**
     * Constructor for CommandAdd
     * @param taskList task list.
     * @param task task to be added.
     */
    public CommandAdd(TaskList taskList, Task task) {
        super(taskList);
        this.task = task;
    }

    @Override
    public String execute() {
        taskList.add(task);
        int size = taskList.getSize();
        return String.format(ADD_TASK_CONFIRMATION, task, size, (size == 1 ? "task" : "tasks"));
    }

    public Task getTask() {
        return task;
    }
}
