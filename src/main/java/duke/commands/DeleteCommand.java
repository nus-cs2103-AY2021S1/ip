package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    public int num;

    public DeleteCommand(int num) {
        this.num = num;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.formatDeleteTask(tasks.lst, num);
        tasks.deleteTask(num);
        storage.saveTaskList(tasks.lst);
    }
}
