package duke.command;

import java.util.List;

import duke.exception.DukeException;
import duke.logic.Parser;
import duke.logic.Storage;
import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents a command to mark tasks as done.
 */
public class DoneCommand extends Command {

    /**
     * The task number on the list to be marked as done.
     */
    private final String taskNumber;

    /**
     * Constructs a command that marks a task as done.
     *
     * @param taskNumber The task number to be marked as done.
     */
    public DoneCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        List<Task> tasks = manager.getTasks();
        Task task = Parser.parseTaskNumber(taskNumber, tasks);
        manager.markTaskAsDone(task);
        storage.saveTasks(tasks);
        return ui.showDoneMessage(task);
    }
}
