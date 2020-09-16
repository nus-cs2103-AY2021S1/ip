package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;
import duke.ui.UiForGui;

/**
 * Encapsulates a list command for the Duke program. This is the command that lists out the tasks in the task list
 * and presents them to the user. The format for this command is: "list".
 */
public class ListCommand extends Command {

    /** Valid words to invoke the list command */
    public static final List<String> COMMAND_WORDS = new ArrayList<>(List.of("list", "ls", "l"));

    /**
     * Executes the command in the CLI version of Duke and lists out the tasks in the task list and presents them to
     * the user.
     *
     * @param tasks The list of tasks in the program.
     * @param ui The Ui object being used in the program.
     * @param storage The Storage object being used in the program.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }

    /**
     * Executes the command in the GUI version of Duke and lists out the tasks in the task list and presents them to
     * the user.
     *
     * @param tasks The list of tasks in the program.
     * @param uiForGui The UiForGui object being used in the program.
     * @param storage The Storage object being used in the program.
     */
    @Override
    public String execute(TaskList tasks, UiForGui uiForGui, Storage storage) {
        return uiForGui.showTaskList(tasks);
    }
}
