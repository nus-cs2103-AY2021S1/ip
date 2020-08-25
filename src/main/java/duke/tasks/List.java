package duke.tasks;

import duke.tool.Storage;
import duke.tool.TaskList;

import duke.Ui;

public class List extends Task{
    public List() {
        super("list", true);
    }

    @Override
    public void excute(TaskList tasklist, Ui ui, Storage storage) {
        ui.listTasks(tasklist);
    }
}
