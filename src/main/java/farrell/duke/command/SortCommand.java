package main.java.farrell.duke.command;

import main.java.farrell.duke.DukeException;
import main.java.farrell.duke.task.TaskList;

public class SortCommand extends Command {
    private String sortType;
    private boolean sortAscending;

    public SortCommand(String sortType) {
        this(sortType, false);
    }

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
        return sortedList;
    }
}
