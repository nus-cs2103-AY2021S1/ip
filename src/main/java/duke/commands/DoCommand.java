package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class DoCommand extends Command {
    private final int taskIndex;

    public DoCommand(int taskIndex) {
        this.commandName = "Do";
        this.taskIndex = taskIndex;
        this.isExit = false;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task task = list.getTaskAtIndex(taskIndex);
        task.setDone();
        list.markTaskAsDone(task);
        storage.write(list.getList());
        ui.showDone(task);
        list.printList("Undone");
    }
}
