package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;

public class ListCommand implements Command {

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        ArrayList<String> tasksListRepr = tasks.getListRepr();
        ui.printWithWrapper(tasksListRepr, true, false);
    }
}
