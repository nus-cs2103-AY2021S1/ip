package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.taskListHandler;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public abstract class AddAbstractTaskCommand extends Command {
    protected final Task newTask;
    // Contains the tasktype command which adds task to the list
    // Parent for todo, event and deadline
    public AddAbstractTaskCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public void execute(taskListHandler handler, Storage storage) {
        // Create and store events given in list
        try {
            ArrayList<Task> tasks = handler.getTasks();
            handler.addToList(newTask);
            Ui.printSuccess("add", newTask, tasks.size());
            storage.saveToFile(tasks);
        } catch (DukeException e){
            e.printStackTrace(System.out);
            DukeException.tryAgain();
        }
    }
}
