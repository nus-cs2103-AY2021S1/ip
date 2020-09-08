package main.java.farrell.duke;

public class FindCommand extends Command {
    private String filterString;

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
