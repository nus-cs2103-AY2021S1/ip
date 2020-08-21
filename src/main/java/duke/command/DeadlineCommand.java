package duke.command;

import duke.*;
import duke.task.Deadline;
import duke.task.Task;

/**
 * Represent a "Deadline" Command
 * A <code>DeadlineCommand</code> object corresponds to a command with an input of "Deadline"
 * and contains a description and date as a String
 */
public class DeadlineCommand extends Command {

    String description, date;

    /**
     * Constructor of the DeadlineCommand class
     *
     * @param description description of the task
     * @param date date of the task
     */
    public DeadlineCommand(String description, String date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Creates a new Task based on the description and date.
     * Adds the task into the taskList object and prints the corresponding messages.
     * Save the task into the datafile.
     *
     * @param taskList The TaskList Object that handles the task operations
     * @param ui The UserInterface object that handles the interaction with users
     * @param storage The Storage Object that handles reading and writing from the datafile
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = new Deadline(description, date);
        taskList.addTask(task);
        ui.printAddTask(taskList);
        storage.save(task);
    }
}
