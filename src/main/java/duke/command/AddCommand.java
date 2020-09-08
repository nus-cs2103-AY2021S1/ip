package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class AddCommand extends Command {
    protected Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    public String execute(TaskList userTasks, Storage storage) {
        userTasks.addTask(task);
        storage.saveToFile(userTasks.getTaskList());
        response = new Ui().taskAddedMessage(task, userTasks.getTaskListSize());
        return getResponse();
    }
}