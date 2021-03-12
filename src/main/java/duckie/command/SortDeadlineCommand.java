package duckie.command;

import duckie.Storage;
import duckie.exception.DuckieException;
import duckie.exception.DuckieNoListException;
import duckie.exception.DuckieNoMatchingTasksException;
import duckie.task.Deadline;
import duckie.task.TaskList;
import duckie.ui.Ui;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Command to sort deadline tasks based on dates.
 */
public class SortDeadlineCommand extends Command {
    private String input;

    /**
     * Sorts the tasks based on deadlines.
     *
     * @param tasks TaskList containing all the tasks.
     * @param ui Ui to interact with the users.
     * @param storage Storage to write to File.
     * @return output A list of all the deadline tasks sorted.
     * @throws DuckieException NoListException can be thrown.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DuckieException {
        if (tasks.getTaskList().size() == 0) {
            throw new DuckieNoListException();
        }

        ArrayList<Deadline> allDeadlineTasks = tasks.getAllDeadlineTasks();
        if (allDeadlineTasks.isEmpty()) {
            throw new DuckieNoMatchingTasksException("Deadline");
        }

        Collections.sort(allDeadlineTasks);

        int index = 1;
        String output = "Quack! The following are your Deadline tasks from the earlist to latest. \n";
        for (Deadline d1 : allDeadlineTasks) {
            output += index + ". " + d1 + "\n";
            index++;
        }
        return output;
    }
}
