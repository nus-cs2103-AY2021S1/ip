package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand implements Command {

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        ArrayList<String> tasksListRepr = tasks.getListRepr();
        ui.printWithWrapper(tasksListRepr, true, false);
    }
}
