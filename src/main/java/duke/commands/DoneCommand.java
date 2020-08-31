package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {

    public static final String commandWord = "add";

    public DoneCommand(String attributes) {
    }

    @Override
    public boolean run(TaskList taskList, Storage storage, Ui ui) {
        return true;
    }
}
