package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents command that is specific to the find command.
 */
public class FindCommand extends Command {

    private String input;

    /**
     * Creates FindCommand object.
     * @param input input of user.
     */
    public FindCommand(String input) {
        this.isExit = false;
        this.input = input;
    }

    /**
     * Executes find command of user.
     * @param taskList tasks of user.
     * @param ui user interface object.
     * @param storage Storage object to retrieve and store data from file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        assert taskList != null : "TaskList is null";
        assert ui != null : "ui is null";
        assert storage != null : "storage is null";
        ui.setFindTaskByKeywordMessage(input.substring(5), taskList.getList());
    }

}
