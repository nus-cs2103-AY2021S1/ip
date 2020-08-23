package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeIOException;

public class SaveCommand implements Command {

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws DukeIOException {
        ArrayList<String> data = tasks.getData();
        storage.save(data);
        ArrayList<String> toPrint = new ArrayList<>(List.of("Saved successfully!"));
        ui.printWithWrapper(toPrint, false, false);
    }
}