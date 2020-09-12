package clippy.command;

import clippy.storage.Storage;
import clippy.task.Task;
import clippy.task.TaskList;
import clippy.task.ToDo;
import clippy.ui.Ui;

public class AddToDoCommand extends AddCommand {
    public AddToDoCommand(String taskDescription) {
        super(taskDescription);
    }
    
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new ToDo(taskDescription);
        tasks.add(newTask);
        String output = ui.showAdded(newTask, tasks.size());

        tasks.updateAllTaskIndices();
        storage.save(tasks);
        
        return output;
    }
}
