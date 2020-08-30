package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeTaskNotFoundException;
import duke.task.Task;

/**
 * Represents a command which finds a task.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Creates a new instance of a FindCommand.
     *
     * @param keyword Keyword to find tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Executes the operation to find matching tasks.
     *
     * @param tasks TaskList linked to the program.
     * @param ui Ui linked to the program.
     * @param storage Storage linked to the program.
     * @return String that contains the executed FindCommand.
     * @throws DukeTaskNotFoundException If no matching tasks are found.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeTaskNotFoundException {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                taskArrayList.add(task);
            }
        }
        if (!taskArrayList.isEmpty()) {
            return ui.showMatchingTask(taskArrayList);
        } else {
            throw new DukeTaskNotFoundException(" NO MATCHING TASK FOUND. \n PLEASE TRY AGAIN. ");
        }
    }

    /**
     * Returns a boolean that dictates if the program is running.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
