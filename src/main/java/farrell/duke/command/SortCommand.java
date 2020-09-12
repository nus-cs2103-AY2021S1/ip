package main.java.farrell.duke.command;

import main.java.farrell.duke.DukeException;
import main.java.farrell.duke.task.TaskList;

public class SortCommand extends Command {
    private String sortType;
    private boolean sortAscending;

    /**
     * Creates a SortCommand with a specified sorting type in descending order.
     *
     * @param sortType The type of sort to perform (description or date).
     */
    public SortCommand(String sortType) {
        this(sortType, false);
    }

    /**
     * Creates a SortCommand with a specified sorting type and sorting order.
     * The default sorting order is descending.
     *
     * @param sortType The type of sort to perform (description or date).
     * @param sortAscending Whether to sort in ascending order instead.
     */
    public SortCommand(String sortType, boolean sortAscending) {
        this.sortType = sortType;
        this.sortAscending = sortAscending;
        type = CommandType.SORT;
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        String sortedList = "";
        switch (sortType) {
        case "DESCRIPTION":
            sortedList = taskList.sortByDescriptionToString(sortAscending);
            break;
        case "DATE":
            sortedList = taskList.sortByTimeToString(sortAscending);
            break;
        default:
            throw new DukeException("Unknown sort type!");
        }
        return "Here are your tasks sorted by " + sortType.toLowerCase() + "\n" + sortedList;
    }
}
