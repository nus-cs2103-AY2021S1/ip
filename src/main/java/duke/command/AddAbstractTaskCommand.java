package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;
import duke.Ui;
import duke.task.Task;

/**
 * Generic add task command which inherits from generic command class.
 * Parent to TodoCommand, DoneCommand and EventCommand.
 * Takes in new task to be added to task list.
 */
public abstract class AddAbstractTaskCommand extends Command {
    protected final Task newTask;

    public AddAbstractTaskCommand(Task newTask) {
        this.newTask = newTask;
    }

    /**
     * Generic method for adding new task to the list, printing success and saving updated list to save file.
     *
     * @param handler Task list.
     * @param storage Storage instance.
     */
    @Override
    public void execute(TaskListHandler handler, Storage storage) {
        try {
            ArrayList<Task> taskList = handler.getTasks();
            handler.addToList(newTask);
            Ui.printSuccess("add", newTask, taskList.size());
            storage.saveToFile(taskList);
        } catch (DukeException e) {
            e.printStackTrace(System.out);
            DukeException.tryAgain();
        }
    }
}
