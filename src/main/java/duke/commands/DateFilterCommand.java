package duke.commands;

import java.time.LocalDate;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that filters the list with the given date.
 * @version 1.0
 */
public class DateFilterCommand extends Command {
    private LocalDate date;

    /**
     * Creates a new DateFilterCommand object with the given date.
     *
     * @param date A LocalDate object storing the given date.
     */
    public DateFilterCommand(LocalDate date) {
        this.commandName = "DateFilter";
        this.date = date;
        this.isExit = false;
    }

    /**
     * Prints all tasks in the specified TaskList filtered by the given date.
     * Shows action feedback to user.
     *
     * @param list A TaskList object of which the command is executed on.
     * @param ui An UI object to interact with the user if required by the command.
     * @param storage A Storage object to write/access information to/from a file if required by the command.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showDateFilterList();
        list.printList(date);
    }
}
