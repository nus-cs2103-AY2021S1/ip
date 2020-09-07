package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.UiForGui;
import duke.exception.TaskIndexOutOfBoundsException;
import duke.task.Task;

/**
 * Encapsulates a done command for the Duke program. This is the command that marks tasks as completed in the task list.
 * The format for this command is: "done x" where x is in the index of a task in the task list.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private int taskIndex;
    private int userSpecifiedIndex;

    /**
     * Creates and initializes a DoneCommand object.
     *
     * @param taskIndex The index of the task in the list.
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
        this.userSpecifiedIndex = taskIndex + 1;
    }

    /**
     * Executes the command. If successful, it will mark a task as done in the task list.
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
            Task completedTask = tasks.getTask(taskIndex);
            completedTask.setDone(true);
            ui.showReplyForDoneTask(completedTask);
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

    @Override
    public String execute(TaskList tasks, UiForGui uiForGui, Storage storage) throws TaskIndexOutOfBoundsException {
        try {
            Task completedTask = tasks.getTask(taskIndex);
            completedTask.setDone(true);
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            return uiForGui.showReplyForDoneTask(completedTask);
        } catch (IndexOutOfBoundsException e) { // User requests for a task with an index not within the current task
            // list
            throw new TaskIndexOutOfBoundsException(Integer.toString(userSpecifiedIndex));
        }
    }
}
