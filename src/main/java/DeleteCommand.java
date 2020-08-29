import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The DeleteCommand inherits from Command and is used to delete tasks in the saved TaskList.
 */
class DeleteCommand extends Command {
    protected int taskNum;

    /**
     * The constructor for DeleteCommand which takes in the task number to be deleted.
     * @param taskNum Task number to be deleted
     */
    DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * The execute method of DeleteCommand is used to delete the specified task from
     * the list of tasks and print the outcome.
     *
     * @param tasks This is the saved TaskList in saved file.
     * @return A boolean to indicate whether the program should be exited.
     */
    @Override
    boolean execute(TaskList tasks) {
        Ui.printTask(tasks.deleteTask(taskNum), Action.DELETE, tasks.getSize());
        return false;
    }
}