package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class AddCommand extends Command {
    private Task newTask;

    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    public void execute (Ui ui, Storage listStorage, TaskList taskList) {
        taskList.add(this.newTask);
        listStorage.addTask(this.newTask);
        ui.addTask(this.newTask, taskList);
    }
}
