package main.java.farrell.duke.command;

import main.java.farrell.duke.DukeException;
import main.java.farrell.duke.task.TaskList;

public class FindCommand extends Command {
    private String filterString;

    /**
     * Creates a FindCommand with a specified filterString.
     *
     * @param filterString The string to find tasks by.
     */
    public FindCommand(String filterString) {
        this.filterString = filterString;
        type = CommandType.FIND;
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        String filteredList = taskList.filteredToString(filterString);
        return "Here are the matching tasks in your list:\n" + filteredList;
    }
}
