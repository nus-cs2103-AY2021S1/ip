package duke.command;

import duke.*;

public class ExitCommand extends Command {

    public ExitCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks.getTasks());
        ui.printExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
