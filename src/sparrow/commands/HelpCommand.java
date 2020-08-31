package sparrow.commands;

import sparrow.data.task.TaskList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    // TODO: implement proper help documentation
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.replyToUser("You asked for help?");
    }
}
