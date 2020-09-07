package duckie.command;

import java.util.ArrayList;

import duckie.Storage;
import duckie.exception.DuckieException;
import duckie.exception.DuckieNoMatchingTasksException;
import duckie.task.Task;
import duckie.task.TaskList;
import duckie.ui.Ui;

/**
 * Command to retrieve all Tasks containing the keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Instantiates a FindCommand object.
     *
     * @param keyword Keyword that is going to be matched.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds the matching tasks and display them.
     *
     * @param tasks TaskList containing all the tasks.
     * @param ui Ui to interact with the users.
     * @param storage Storage to write to File.
     * @return output A list of all the matching tasks.
     * @throws DuckieException Throws NoMatchingTask Exception.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DuckieException {
        assert keyword instanceof String : "Keyword is not in the right data type";
        ArrayList<Task> filteredList = tasks.filterList(keyword);
        if (filteredList.size() == 0) {
            throw new DuckieNoMatchingTasksException();
        } else {
            int index = 1;
            String output = "Quack! Duckie found these matching tasks: \n";
            for (Task task : filteredList) {
                output += index + ". " + task + "\n";
                index++;
            }
            return output;
        }
    }

}
