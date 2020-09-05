package duke.command;

import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to see all the tasks on a certain date.
 */
public class ViewCommand extends Command {
    /** The date on which you want to view the tasks for. */
    private String date;

    /**
     * Creates a new view command with the specified date.
     *
     * @param date The date on which you want to view the tasks for.
     */
    public ViewCommand(String date) {
        this.date = date;
    }

    /**
     * View the tasks on the date in the specified task list.
     *
     * @param tasks The task list the command is executed with.
     * @param storage The storage the command is executed with.
     * @return A command response that represents the result of completing a view command.
     * @throws DukeException If there was a problem with executing the command.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            return new CommandResponse(Ui.respondViewTasks(tasks, Parser.parseDate(date)), this.isExit());
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! The date is not valid.");
        }
    }
}
