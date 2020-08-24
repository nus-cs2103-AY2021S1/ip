package duke;

public class ToDoCommand extends Command {
    private Task task;
   ToDoCommand(Task task) {
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
