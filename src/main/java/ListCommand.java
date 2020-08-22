import java.util.ArrayList;

public class ListCommand implements Command {

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        ArrayList<String> tasksListRepr = tasks.getListRepr();
        ui.printWithWrapper(tasksListRepr, true, false);
    }
}
