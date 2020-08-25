package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.taskListHandler;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Objects;

public class DoneCommand extends AbstractModifyTaskCommand {

    public DoneCommand(Task task) {
        super(task);
    }

    @Override
    public void execute(taskListHandler handler, Storage storage) {
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
