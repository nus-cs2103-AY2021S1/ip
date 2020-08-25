package duke.tasks;

import duke.tool.Storage;
import duke.tool.TaskList;

import duke.Ui;

public class Done extends Task{
    int i;
    public Done(int i) {
        super("done", true);
        this.i = i;
    }

    @Override
    public void excute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.markDone(this.i);
        ui.showDoneMessage(tasklist, this.i);
        storage.writeData(tasklist.taskList);
    }
}
