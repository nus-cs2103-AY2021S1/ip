package duckie.command;

import duckie.Storage;
import duckie.exception.DuckieException;
import duckie.exception.DuckieNoListException;
import duckie.exception.DuckieNoMatchingTasksException;
import duckie.task.Event;
import duckie.task.TaskList;
import duckie.ui.Ui;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Command to sort event tasks based on dates.
 */
public class SortEventCommand extends Command {
    private String input;

    /**
     * Sorts the tasks based on events.
     *
     * @param tasks TaskList containing all the tasks.
     * @param ui Ui to interact with the users.
     * @param storage Storage to write to File.
     * @return output A list of all the event tasks sorted.
     * @throws DuckieException NoListException can be thrown.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DuckieException {
        if (tasks.getTaskList().size() == 0) {
            throw new DuckieNoListException();
        }

        ArrayList<Event> allEventTasks = tasks.getAllEventTasks();

        if (allEventTasks.isEmpty()) {
            throw new DuckieNoMatchingTasksException("Event");
        }

        Collections.sort(allEventTasks);

        int index = 1;
        String output = "Quack! The following are your Event tasks from the earlist to latest. \n";
        for (Event d1 : allEventTasks) {
            output += index + ". " + d1 + "\n";
            index++;
        }
        return output;
    }
}
