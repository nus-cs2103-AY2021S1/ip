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
    public String act(TaskList list) {
        return Ui.getSearchResult(list.find(searchText));
    }

    @Override
    public String undo(TaskList list) {
        return Ui.getCannotUndoMessage();
    }
}
