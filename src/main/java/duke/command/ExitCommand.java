package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.UiForGui;

/**
 * Encapsulates an exit command for the Duke program. This is the command that terminates the program. The format for
 * this command is: "bye".
 */
public class ExitCommand extends Command {

    /**
     * Creates and initializes an ExitCommand object. Its isExit boolean flag is marked as true to indicate to the
     * program that it should terminate.
     */
    public ExitCommand() {
        setExit(true);
    }

    /**
     * Executes the command and terminates the program.
     *
     * @param tasks The list of tasks in the program.
     * @param ui The Ui object being used in the program.
     * @param storage The Storage object being used in the program.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public String execute(TaskList tasks, UiForGui uiForGui, Storage storage) {
        return uiForGui.showGoodbye();
    }
}
