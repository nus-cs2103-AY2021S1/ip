package Command;

import DukeComponent.Ui;
import TaskList.TaskList;

public class FindCommand extends Command {
    private String searchText;

    public FindCommand(String searchText) {
        super(CommandType.FIND);
        this.searchText = searchText;
    }

    @Override
    public void act(TaskList list) {
        Ui.searchResult(list.find(searchText));
    }
}
