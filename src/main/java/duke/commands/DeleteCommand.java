package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.commandName = "Delete";
        this.taskIndex = taskIndex;
        this.isExit = false;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task task = list.getTaskAtIndex(taskIndex);
        list.deleteTask(task);
        storage.write(list.getList());
        ui.showDelete(task);
        list.printList("All");
    }
}
