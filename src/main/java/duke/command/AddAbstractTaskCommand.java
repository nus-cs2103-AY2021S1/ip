package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public abstract class AddAbstractTaskCommand extends Command {
    protected final Task newTask;

    // Contains the tasktype command which adds task to the list
    public AddAbstractTaskCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public void execute(TaskListHandler handler, Storage storage) {
        // Create and store events given in list
        try {
            ArrayList<Task> taskList = handler.getTaskList();
            handler.addToList(newTask);
            Ui.printSuccess("add", newTask, taskList.size());
            storage.saveToFile(taskList);
        } catch (DukeException e){
            e.printStackTrace(System.out);
            DukeException.tryAgain();
        }
    }
}
