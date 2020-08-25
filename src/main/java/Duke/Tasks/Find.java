package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui;

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
