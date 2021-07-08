package duke.command;

import duke.Storage;
import duke.TaskList;

public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye!";
    }
}
