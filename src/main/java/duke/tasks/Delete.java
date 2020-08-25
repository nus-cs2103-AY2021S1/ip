package duke.tasks;

import duke.tool.Storage;
import duke.tool.TaskList;

import duke.Ui;

public class Delete extends Task{
    int i;
    public Delete(int i) {
        super("delete", true);
        this.i = i;
    }

    @Override
    public void excute(TaskList tasklist, Ui ui, Storage storage) {
        Task removed = tasklist.delete(i);
        ui.showDeleteMessage(tasklist, removed);
        storage.writeData(tasklist.taskList);
    }
}
