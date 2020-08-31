package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    public static final String commandWord = "add";

    public FindCommand(String attributes) {

    }

    @Override
    public boolean run(TaskList taskList, Storage storage, Ui ui) {

        return true;
    }
}
