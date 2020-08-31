package duke.command;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.Storage;
import duke.task.Task;
import duke.task.Tasks;
import duke.ui.Ui;

/**
 * The Find date command finds tasks with the same date.
 */
public class FindDateCommand extends FindCommand {
    /**
     * The Date.
     */
    private final LocalDate date;

    /**
     * Instantiates a new Find date command.
     *
     * @param date the date to be found.
     */
    public FindDateCommand(LocalDate date) {
        this.commandType = CommandType.FIND;
        this.findCommandType = FindCommandType.DATE;
        this.date = date;
    }

    /**
     * Find and returns a response consisting a message with tasks on the date.
     *
     * @param tasks   the task list.
     * @param ui      interacts with user.
     * @param storage loads and save tasks.
     * @return the response to find date command.
     */
    @Override
    public CommandResponse execute(Tasks tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.findByDate(this.date);
        return new CommandResponse(ui.getFoundMessage(date, taskList), this.isExit());
    }
}
