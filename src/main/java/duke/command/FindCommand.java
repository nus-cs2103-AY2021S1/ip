package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the Command to find tasks that matches a provided keyword.
 */
public class FindCommand implements Command {

    private final String[] keywords;

    /**
     * Initializes a FindCommand.
     *
     * @param keywords The search keywords.
     */
    public FindCommand(String ...keywords) {
        this.keywords = keywords;
    }

    /**
     * Finds the list of tasks that matches the keyword, then prints it to the console.
     *
     * @param ui      The ui of Duke.
     * @param storage The storage object.
     * @param tasks   The taskList.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        ArrayList<String> findTasksRepr = tasks.find(keywords);
        ui.printWithWrapper(findTasksRepr, true, false);
    }
}
