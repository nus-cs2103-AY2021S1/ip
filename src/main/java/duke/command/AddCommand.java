package duke.command;

import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * This Command will add a Task to the TaskList.
 */
public class AddCommand implements Command {
    private Task task;

    /**
     * Creates an AddCommand.
     *
     * @param task the task to add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(Ui ui, TaskList list) {
        list.add(task);
        ui.say(String.format("Got it, I've added this task:\n  %s\n%s", task.displayString(),
                Helper.getNumberOfTasksString(list)));
    }
}
