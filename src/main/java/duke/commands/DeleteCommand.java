package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {

    public static final String commandWord = "add";

    public DeleteCommand(String attributes) {

    }

    @Override
    public boolean run(TaskList taskList, Storage storage, Ui ui) {
        return true;
    }
}
