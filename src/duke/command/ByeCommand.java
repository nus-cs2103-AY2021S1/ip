package duke.command;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

/**
 * Represents a ByeCommand for exiting the program.
 */
public class ByeCommand extends Command{

    /**
     * Exits the program
     *
     * @param tasks The TaskList manipulated by the program
     * @param ui The Ui which will generate outputs significant to the user.
     * @param storage The Storage for recording tasks passed in by user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String byeMessage = "\t Bye. Hope to see you again soon!\n";
        ui.showMessage(byeMessage);
    }

    /**
     * Indicates whether the program should exit after executing this Command.
     *
     * @return True since a ByeCommand indicates that the user wants to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
