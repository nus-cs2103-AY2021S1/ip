package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Objects;

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
            ArrayList<Task> taskList = handler.getTaskList();
            task.markAsDone();
            Ui.printSuccess("done", task, taskList.size());
            storage.saveToFile(taskList);
        } catch (DukeException e) {
            e.printStackTrace(System.out);
            DukeException.tryAgain();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task taskx = (Task) o;
        return taskx == task;
    }

    @Override
    public int hashCode() {
        return Objects.hash(task);
    }
}
