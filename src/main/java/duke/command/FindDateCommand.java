package duke.command;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.Tasks;

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
     * Find and print tasks with the date.
     *
     * @param tasks   the task list.
     * @param ui      interacts with user.
     * @param storage loads and save tasks.
     */
    public void execute(Tasks tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.findByDate(this.date);
        ui.printFound(date, taskList);
    }
}
