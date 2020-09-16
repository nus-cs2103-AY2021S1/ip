package duke.command;

import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(CommandType.EXIT);
    }

    @Override
    public String execute(Ui ui, TaskList taskList) {
        return ui.printGoodbyeMessage();
    }
}
