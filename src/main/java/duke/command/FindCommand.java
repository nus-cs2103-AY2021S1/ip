package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.Tasks;

import java.time.LocalDate;

import java.util.ArrayList;

/**
 * The Find command finds tasks with the same date.
 */
public class FindCommand extends Command {
    /**
     * The Command type.
     */
    private final CommandType commandType;
    /**
     * The Date.
     */
    private final LocalDate date;

    /**
     * Instantiates a new Find command.
     *
     * @param date the date.
     */
    public FindCommand(LocalDate date) {
        this.commandType = CommandType.FIND;
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
