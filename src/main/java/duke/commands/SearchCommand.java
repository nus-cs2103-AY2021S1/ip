package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class SearchCommand extends Command {
    final String[] keyword;

    /**
     * Initializes instance of SearchCommand.
     * @param keyword Keyword for search criteria.
     */
    public SearchCommand(String[] keyword) {
        assert keyword.length > 0 : "You must have a keyword for find!";
        this.keyword = keyword;
    }

    /**
     * Calls the displayList function in the ui class which prints out a provided
     *  list of tasks.
     * @param ui A duke.Ui instance to enable calling of duke.Ui functions.
     * @param storage A duke.Storage instance to enable calling of duke.Storage functions.
     * @return String being printed.
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        return ui.displayList(TaskList.searchList(this.keyword),
                "Doge found the following tasks you asked for!");
    }
}
