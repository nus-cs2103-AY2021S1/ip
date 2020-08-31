package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    public static final String commandWord = "add";

    public ListCommand(String attributes) {

    }

    @Override
    public boolean run(TaskList taskList, Storage storage, Ui ui) {

        return true;
    }
}
