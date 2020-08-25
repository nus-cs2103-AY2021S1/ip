package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class DeleteCommand extends AbstractModifyTaskCommand {

    public DeleteCommand(Task task) {
        super(task);
    }

    @Override
    public void execute(TaskListHandler handler, Storage storage) {
        try {
            ArrayList<Task> taskList = handler.getTaskList();
            handler.getTaskList().remove(task);
            Ui.printSuccess("delete", task, taskList.size());
            storage.saveToFile(taskList);
        } catch (DukeException e) {
            e.printStackTrace(System.out);
            DukeException.tryAgain();
        }
    }
}
