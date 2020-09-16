package duke.command;

import duke.TaskList;
import duke.Ui;

public class HelpCommand extends Command {

    public HelpCommand() {
        super(CommandType.HELP);
    }

    @Override
    public String execute(Ui ui, TaskList taskList) {
        return ui.printHelp();
    }
}
