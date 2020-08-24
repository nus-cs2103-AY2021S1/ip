package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {

    public ExitCommand(String[] stringArray) {
        super(stringArray);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //Empty as program will exit.
    }

    @Override
    public boolean isExit() {
        return true;
    };
}
