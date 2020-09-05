package duke.command;

import java.util.List;

import duke.exception.DukeException;
import duke.logic.Parser;
import duke.logic.Storage;
import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents a command to delete tasks.
 */
public class DeleteCommand extends Command {

    /**
     * The task number on the list to be deleted.
     */
    private final String taskNumber;

    /**
     * Constructs a command that deletes a task.
     *
     * @param taskNumber The task number to be deleted.
     */
    public DeleteCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        List<Task> tasks = manager.getTasks();
        Task task = Parser.parseTaskNumber(taskNumber, tasks);
        manager.deleteTask(task);
        storage.saveTasks(tasks);
        return ui.showDeleteMessage(task, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
