package blue.command;

import java.time.LocalDate;
import java.util.ArrayList;

import blue.Storage;
import blue.task.Task;
import blue.task.Tasks;
import blue.ui.Ui;

/**
 * The Find today command finds tasks today.
 */
public class FindTodayCommand extends FindDateCommand {
    /**
     * Instantiates a new Find today command.
     */
    public FindTodayCommand() {
        super(LocalDate.now());
    }

    /**
     * Find and returns a response consisting a message with tasks today.
     *
     * @param tasks   the task list.
     * @param ui      interacts with user.
     * @param storage loads and save tasks.
     * @return the response to find today command.
     */
    @Override
    public CommandResponse execute(Tasks tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.findByDate(super.date);
        return new CommandResponse(ui.getFoundMessage(taskList), this.isExit());
    }
}
