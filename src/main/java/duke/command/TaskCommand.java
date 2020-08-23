package duke.command;

import duke.Storage;
import duke.task.Tasks;
import duke.Ui;
import duke.exception.SaveTaskFailedException;
import duke.task.Task;

import java.io.IOException;

public class TaskCommand extends Command {
    private final CommandType commandType;
    private final Task task;
    
    public TaskCommand(Task task) {
        this.commandType = CommandType.TASK;
        this.task = task;    
    }
    
    public void execute(Tasks tasks, Ui ui, Storage storage) throws SaveTaskFailedException {        
        tasks.addTask(this.task);
        try {
            storage.addTask(task);
        } catch (IOException ex) {
            throw new SaveTaskFailedException(tasks.getSize());
        }
        ui.printAddTask(task, tasks.getSize());
    }
}
