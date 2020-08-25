package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class DoneCommand extends AbstractModifyTaskCommand {

    public DoneCommand(Task task) {
        super(task);
    }

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
}
