package duke.command;

import duke.error.IncorrectFormat;
import duke.parts.Storage;
import duke.parts.TaskList;
import duke.parts.Ui;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command{

    String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IncorrectFormat, IOException {
        ArrayList<Task> found = tasks.find(input);
        ui.printFind(found);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
