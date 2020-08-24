package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to see all the tasks on a certain date.
 */
public class ViewCommand extends Command {
    /** The date on which you want to view the tasks for. */
    private String date;

    /**
     * Creates a new view command with the specified date.
     * @param date The date on which you want to view the tasks for.
     */
    public ViewCommand(String date) {
        this.date = date;
    }

    /**
     * View the tasks on the date in the specified task list.
     * @param tasks The task list the command is executed with.
     * @param ui The ui the command is executed with.
     * @param storage The storage the command is executed with.
     * @throws DukeException If there was a problem with executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            ui.printViewTasks(tasks, LocalDate.parse(date));
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! The date is not valid.");
        }
    }
}
