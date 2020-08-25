package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui;

public class List extends Task{
    public List() {
        super("list", true);
    }

    @Override
    public void excute(TaskList tasklist, Ui ui, Storage storage) {
        ui.listTasks(tasklist);
    }
}
