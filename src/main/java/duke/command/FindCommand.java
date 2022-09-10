package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

public class FindCommand extends Command {

    private String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm.toLowerCase();
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Lemme look through them records real lickity-split!\n");
        buffer.append(tasks.listSearch(searchTerm));
        return buffer.toString();
    }
}
