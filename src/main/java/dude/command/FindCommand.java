package dude.command;

import java.util.ArrayList;

import dude.task.Task;
import dude.ui.Ui;
import dude.util.CommandException;
import dude.util.Storage;
import dude.util.TaskList;

/**
 * The command gets the items in the tasklist that contains the keyword.
 */

public class FindCommand extends Command {
    private String keyWord;

    /**
     * Constructor for FindCommand class.
     *
     * @param action action the command is to perform.
     * @param keyWord the keyword to find in the tasklist.
     */
    public FindCommand(String action, String keyWord) {
        super(action);
        this.keyWord = keyWord;
    }

    /**
     * Adds the task to the TaskList, stores the data and displays the resulting output.
     *
     * @param tasks TaskList containing all the current tasks.
     * @param ui Ui class to display output.
     * @param storage Storage class to store tasks.
     * @throws CommandException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandException {
        ArrayList<Task> searchList = tasks.search(keyWord);
        Command command = new ListCommand("list");
        command.execute(new TaskList(searchList), ui, storage);
        if (searchList.size() == 0) {
            throw new CommandException("The keyword you searched for does not result in any results.");
        }
    }
}
