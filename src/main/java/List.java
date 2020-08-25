import java.util.ArrayList;

public class List extends Task{
    public List() {
        super("list", true);
    }

    @Override
    public void excute(TaskList tasklist, Ui ui, Storage storage) {
        ui.listTasks(tasklist);
    }
}
