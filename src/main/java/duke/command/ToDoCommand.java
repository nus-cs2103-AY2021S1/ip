package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

public class ToDoCommand extends Command {
    private Task task;
   public ToDoCommand(Task task) {
       this.task = task;
   }
    
    @Override
    public void execute(TaskList taskItems, Ui ui, Storage storage) throws DukeException {
        taskItems.addTask(task);
        ui.addTaskReply(task, taskItems);
        storage.saveTaskToMemory(taskItems.getAll());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
