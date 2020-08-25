package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DoneCommand extends  Command {

    public int num;

    public DoneCommand(int num) {
        this.num = num;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTaskAsDone(num);
        ui.formatMarkAsDone(tasks.lst, num);
        storage.saveTaskList(tasks.lst);
    }
}
