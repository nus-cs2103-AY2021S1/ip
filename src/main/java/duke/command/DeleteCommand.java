package duke.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.exception.TaskIndexOutOfBoundsException;
import duke.task.Task;
import duke.ui.Ui;
import duke.ui.UiForGui;

/**
 * Encapsulates a delete command for the Duke program. This is the command that removes tasks from the task list. The
 * format for this command is: "delete x" where x is in the index of a task in the task list.
 */
public class DeleteCommand extends Command {

    /** Valid words to invoke the delete command */
    public static final List<String> COMMAND_WORDS = new ArrayList<>(List.of("delete", "del"));

    /** Strict number of arguments expected for the delete command */
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private int taskIndex;
    private int userSpecifiedIndex;

    /**
     * Creates and initializes a DeleteCommand object.
     *
     * @param taskIndex The user's full command split into strings separated by whitespaces.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
        this.userSpecifiedIndex = taskIndex + 1;
    }

    /**
     * Executes the command in the CLI version of Duke. If successful, it will remove a task from the task list.
     *
     * @param tasks The list of tasks in the program.
     * @param ui The Ui object being used in the program.
     * @param storage The Storage object being used in the program.
     * @throws TaskIndexOutOfBoundsException If the index of the task specified by the user is not present in the task
     * list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskIndexOutOfBoundsException {
        try {
            Task removedTask = tasks.removeTask(taskIndex);
            ui.showReplyForDeleteTask(removedTask, tasks);
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (IndexOutOfBoundsException e) { // User requests for a task with an index not within the current task
            // list
            throw new TaskIndexOutOfBoundsException(Integer.toString(userSpecifiedIndex));
        }
    }

    /**
     * Executes the command in the GUI version of Duke. If successful, it will remove a task from the task list.
     *
     * @param tasks The list of tasks in the program.
     * @param uiForGui The UiForGui object being used in the program.
     * @param storage The Storage object being used in the program.
     * @throws TaskIndexOutOfBoundsException If the index of the task specified by the user is not present in the task
     * list.
     */
    @Override
    public String execute(TaskList tasks, UiForGui uiForGui, Storage storage) throws TaskIndexOutOfBoundsException {
        try {
            Task removedTask = tasks.removeTask(taskIndex);
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            return uiForGui.showReplyForDeleteTask(removedTask, tasks);
        } catch (IndexOutOfBoundsException e) { // User requests for a task with an index not within the current task
            // list
            throw new TaskIndexOutOfBoundsException(Integer.toString(userSpecifiedIndex));
        }
    }
}
