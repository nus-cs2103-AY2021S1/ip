package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.exceptions.IncompleteDukeCommandException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The {@code SearchCommand} class represents a command that allows the user to search for tasks.
 * The user is able to search for tasks either through their description or
 * their date (if the type of task supports it).
 */
public class SearchCommand extends Command {

    private String searchParameter;
    private List<Task> results;

    /**
     * Constructs a {@code SearchCommand} with the specified {@code searchParameter}.
     *
     * @param searchParameter the string for which to search for.
     */
    public SearchCommand(String searchParameter) {
        this.searchParameter = searchParameter;
        results = new ArrayList<>();
    }

    /**
     * Executes this {@code SearchCommand}.
     *
     * @param list    the currently loaded {@link TaskList} object.
     * @param storage the currently loaded {@link Storage} object.
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        for (Task task : list) {
            if (task.match(searchParameter)) {
                results.add(task);
            }
        }
        super.completed = true;
    }

    /**
     * Prints the result of executing this {@code SearchCommand}.
     *
     * @throws IncompleteDukeCommandException if this {@code DeleteCommand} was not executed.
     */
    @Override
    public String feedback() throws IncompleteDukeCommandException {
        if (super.completed) {
            String resultPrint = "";
            for (Task result : results) {
                if (resultPrint.length() > 0) {
                    resultPrint = resultPrint.concat("\n");
                }
                resultPrint = resultPrint.concat(result.toString());
            }
            return resultPrint;
        } else {
            throw new IncompleteDukeCommandException("Search command was not completed.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
