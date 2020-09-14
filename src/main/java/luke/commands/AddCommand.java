package luke.commands;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.task.Task;
import luke.exception.LukeException;
import java.time.DateTimeException;

public class AddCommand extends Command {

    protected Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws LukeException {
        try {
            tasks.add(this.task);
            storage.save(tasks);
            return ui.showAddResult(this.task, tasks.getSize());
        } catch (DateTimeException dateTimeException) {
            throw new LukeException("Please enter a valid date format.");
        }
    }
}
