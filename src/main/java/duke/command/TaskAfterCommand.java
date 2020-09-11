package duke.command;

import java.time.LocalDate;

import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

/**
 * This class represents the task after command. When executed, the class will display all the task
 * after a certain date.
 */
public class TaskAfterCommand extends Command {
    private LocalDate date;

    /**
     * Constructs a Command to check all the task after a certain date.
     * @param date The specified date
     */
    public TaskAfterCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the TaskAfterCommand. Executing this command will prints out every task in the list that is due
     * after the specified date. Note that it will prints only deadline and event task since todo task does
     * not have any date.
     * @param tasks TaskList of the current task.
     * @param ui Ui to deals with interactions with the user.
     * @param storage Storage to save the data to the hard disk.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sendMessage(ui.showTaskAfter(date, tasks));
        return ui.showTaskAfter(date, tasks);
    }
}
