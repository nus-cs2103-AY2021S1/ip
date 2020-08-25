package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

/**
 * Encapsulates a command to find tasks that match a given keyword
 */
public class FindCommand extends Command {

    final private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.printTasksWithKeyword(tasks.getSublistContainingKeyword(this.keyword));
    }
}
