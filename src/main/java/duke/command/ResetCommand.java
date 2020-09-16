package duke.command;

import duke.TaskList;
import duke.Ui;

public class ResetCommand extends Command {

    public ResetCommand() {
        super(CommandType.RESET);
    }

    @Override
    public String execute(Ui ui, TaskList taskList) {
        return "You're not supposed to see this :o";
    }
}
