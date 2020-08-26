package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

/** This class represents the command of listing the users' tasks. */
public class ListCommand extends Command {
    private LocalDate date;

    /**
     * Constructs a ListCommand object without a date.
     */
    public ListCommand() {
        this.date = null;
    }

    /**
     * Constructs a ListCommand object associated with a date.
     * @param date The date filter of the ListCommand.
     */
    public ListCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Prints all tasks if no date is specified.
     * Prints all tasks on the specified date, if a date is specified.
     * @param taskList The TaskList which contains the tasks to be printed.
     * @param ui The Ui that prints the list to the user.
     * @param storage The Storage associated with the Command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayList(taskList, date);
    }
}
