package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

public class EventCommand extends ToDoCommand {
    public EventCommand(Task task) {
        super(task);
    }

    @Override
    public void execute(TaskList taskItems, Ui ui, Storage storage) throws DukeException {
        super.execute(taskItems, ui, storage);
    }
}
