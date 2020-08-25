package viscount.command;

import viscount.*;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // do nothing
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
