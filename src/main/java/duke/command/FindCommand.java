package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

public class FindCommand extends Command {

    String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm.toLowerCase();
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        System.out.println("Lemme look through them records real lickity-split!");
        tasks.listSearch(searchTerm);
    }
}
