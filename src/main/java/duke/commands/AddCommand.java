package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.task.Task;

public class AddCommand implements Command {
    public Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addToPlanner(task);
        ui.addMessage(task, tasks.getSize());
        storage.save(tasks.getPlanner());
        return false;
    }

}
