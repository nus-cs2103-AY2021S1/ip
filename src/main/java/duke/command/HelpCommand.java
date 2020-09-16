package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class HelpCommand extends Command {

    public HelpCommand(String command) {
        super(command, false);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        String specificHelp = command.substring(5);
        System.out.println("printing:" + specificHelp);
        ui.printHelpInstructions(specificHelp);
    }

    @Override
    public String executeChat(TaskList list, Ui ui, Storage storage) {
        String specificHelp = command.substring(5);
        return ui.printHelpInstructions(specificHelp, true);
    }
}
