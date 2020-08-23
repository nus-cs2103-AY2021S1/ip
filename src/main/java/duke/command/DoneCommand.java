package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidTaskNumberException;
import duke.exception.SaveTaskFailedException;
import duke.task.Tasks;

import java.io.IOException;

public class DoneCommand extends Command {
    private final CommandType commandType;
    private final int taskIndex;
            
    public DoneCommand(int taskIndex) {
        this.commandType = CommandType.DONE;
        this.taskIndex = taskIndex;
    }    
    
    public void execute(Tasks tasks, Ui ui, Storage storage) throws InvalidTaskNumberException, SaveTaskFailedException {
        try {
            tasks.getTask(this.taskIndex).markAsDone();
            storage.updateTasks(tasks);
            ui.printMarkTaskAsDone(tasks.getTask(this.taskIndex));
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidTaskNumberException("The task to be marked as done does not exist!");
        } catch (IOException ex) {
            throw new SaveTaskFailedException(this.taskIndex);
        }
    }
}
