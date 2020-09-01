package duke.command;

import java.util.ArrayList;
import java.util.Objects;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;
import duke.Ui;
import duke.task.Task;

/**
 * Marks a provided task as done.
 * Inherits from AbstractModifyTaskCommand which inherits from generic command class.
 */
public class DoneCommand extends AbstractModifyTaskCommand {

    public DoneCommand(Task task) {
        super(task);
    }

    /**
     * Marks given task as done, printing success and saving updated list to save file.
     *
     * @param handler Task list.
     * @param storage Storage instance.
     */
    @Override
    public void execute(TaskListHandler handler, Storage storage) {
        try {
            ArrayList<Task> tasks = handler.getTasks();
            task.markAsDone();
            Ui.printSuccess("done", task, tasks.size());
            storage.saveToFile(tasks);
        } catch (DukeException e) {
            e.printStackTrace(System.out);
            DukeException.tryAgain();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task1 = (Task) o;
        return task1 == task;
    }

    @Override
    public int hashCode() {
        return Objects.hash(task);
    }
}
