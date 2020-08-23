package duke.commands;

import duke.commands.Command;
import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.ui.UI;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public ExitCommand() {
        super("" , true);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        storage.saveData(taskList, ui);
        ui.displayGoodbye();
    }
}
