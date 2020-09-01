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
     * returns the done message
     * @param tasks
     * @param ui
     * @param storage
     * @return done message
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index);
        String output = ui.displayMarkAsDoneMessage(task);
        tasks.completeTask(index);
        return output;

    }

}
