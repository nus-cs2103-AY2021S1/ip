package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;
import duke.ui.UiForGui;

/**
 * Encapsulates an exit command for the Duke program. This is the command that terminates the program. The format for
 * this command is: "bye".
 */
public class ExitCommand extends Command {

    /** Valid words to invoke the exit command */
    public static final List<String> COMMAND_WORDS = new ArrayList<>(List.of("bye", "b"));

    /**
     * Creates and initializes an ExitCommand object. Its isExit boolean flag is marked as true to indicate to the
     * program that it should terminate.
     */
    public ExitCommand() {
        setExit(true);
    }

    /**
     * Executes the command in the CLI version of Duke and terminates the program.
     *
     * @param tasks The list of tasks in the program.
     * @param ui The Ui object being used in the program.
     * @param storage The Storage object being used in the program.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Executes the command in the GUI version of Duke and terminates the program.
     *
     * @param tasks The list of tasks in the program.
     * @param uiForGui The UiForGui object being used in the program.
     * @param storage The Storage object being used in the program.
     */
    @Override
    public String execute(TaskList tasks, UiForGui uiForGui, Storage storage) {
        return uiForGui.showGoodbye();
    }
}
