package duke.tasks;

import duke.tool.Storage;
import duke.tool.TaskList;
import duke.Ui;

public class Find extends Task{
    protected String toFind;

    public Find(String toFind) {
        super("find", false);
        this.toFind = toFind;
    }

    @Override
    public void excute(TaskList tasklist, Ui ui, Storage storage) {
        ui.listMatchedTasks(tasklist, this.toFind);
    }
}
