package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;


/**
 * Command that searches tasks for keywords.
 */
public class FindCommand extends Command {
    /** Keyword to search for. */
    private String keyword;

    /**
     * Creates a Find Command.
     * @param keyword Keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Prints string produced by getExecuteString method.
     * @param tasks TaskList that is being executed on.
     * @param ui Ui to update the user.
     * @param storage Storage to update the text file.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printString(getExecuteString(tasks, ui, storage));
    }

    /**
     * Searches for TaskList using keyword, and the Ui will display any tasks found.
     * Returns String given by Ui.
     * @param tasks TaskList that is being executed on.
     * @param ui Ui to update the user.
     * @param storage Storage to update the text file.
     * @return String of message.
     * @throws DukeException
     */
    @Override
    public String getExecuteString(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> foundTasks = tasks.findTasks(keyword);
        return ui.getFoundTasksString(foundTasks);
    }
}
