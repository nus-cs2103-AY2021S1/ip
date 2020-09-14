package duke.commands;

import duke.Storage;
import duke.lists.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Command to add a task to the taskList
 */
public class AddTaskCommand extends Command {
    
    private final Task task;

    /**
     * Constructor for the class
     * 
     * @param task task to be added to the taskList
     */
    public AddTaskCommand (Task task) {
        this.task = task;
    }
    
    @Override
    public void executeCommand (Ui ui, Storage storage, TaskList tasklist) {
        tasklist.addTask(task);
        int numberOfTasks = tasklist.size();
        assert numberOfTasks >= 1;
        ui.addTaskMessage(task, numberOfTasks);
        storage.writeDataToFile(tasklist);
    }
    
    @Override
    public String toString() {
        return "AddTaskCommand " + task.toString();
    }
}
