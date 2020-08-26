package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.tasks.Task;

import java.io.IOException;

/** This class represents the command of adding a task. */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs a AddCommand object with a task.
     * @param task The Task associated with the AddCommand.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds task to taskList.
     * Prints the task that has just been added.
     * Adds task to storage.
     * @param taskList The TaskList to add the task to.
     * @param ui The Ui which will print the messages to the user.
     * @param storage The Storage to add the task to.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.add(this.task);
            ui.printAdd(this.task, taskList.size());
            storage.add(this.task);
        } catch(IOException e) {
            System.out.println("Something went wrong!");
        }
    }
}
