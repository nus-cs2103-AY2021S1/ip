package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class AddTaskCommand extends Command {
    
    private final Task task;
    
    public AddTaskCommand (Task task) {
        this.task = task;
    }
    
    @Override
    public void executeCommand (Ui ui, Storage storage, TaskList tasklist) {
        tasklist.addTask(task);
        int numberOfTasks = tasklist.size();
        ui.addTaskMessage(task, numberOfTasks);
        storage.writeToDataFile(tasklist);
    }
    
}
