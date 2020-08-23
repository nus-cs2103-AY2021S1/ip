package duke.command;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

/**
 * Represents a DoNothingCommand.
 */
public class DoNothingCommand extends Command {

    /**
     * Does Nothing.
     *
     * @param tasks The TaskList manipulated by the Command.
     * @param ui The Ui which will generate outputs significant to the user.
     * @param storage The Storage which will record changes of tasks into the file specified by its path.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    /**
     * Indicates whether the program should exit after executing this Command.
     *
     * @return False since the program should still go on after doing nothing.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
