package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import java.io.IOException;

/**
 * Represents an DeleteCommand that is part of the Command class, regarding deleting a task.
 */

public class DeleteCommand extends Command {
    private final int num;

    public DeleteCommand(String command, int num) {
        super(command, false);
        this.num = num;
    }

    /**
     * Executes the command to delete a task from the list of tasks.
     *
     * @param list Tasklist containing tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in a txt file.
     */
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            if (num < 0 || num > list.size()) {
                throw new DukeException("☹ OOPS!!! there is no such task");
            } else {
                Task deleted = list.delete(num - 1);
                ui.deleteMessage(deleted.toString(), list.size());
                storage.deleteTask(num);
            }
        } catch (DukeException | IOException e) {
            ui.errorEncounter(e);
        }
    }
}
