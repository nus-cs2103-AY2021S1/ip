package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a task as done.
     *
     * @param tasks tasklist object.
     * @param ui Ui object.
     * @param storage Storage object.
     * @return done message.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index);
        String output = ui.displayMarkAsDoneMessage(task);
        tasks.completeTask(index);
        return output;

    }

}
