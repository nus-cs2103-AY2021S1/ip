package duke.command;

import duke.util.AliasMap;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a find command in the Duke program.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Initializes a newly created delete command with a keyword.
     *
     * @param keyword keyword to find in task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command.
     *
     * @param tasks TaskList of the program.
     * @param ui user interface of the program.
     * @param storage storage of the program.
     * @param aliasMap alias mapping.
     * @return the execution message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, AliasMap aliasMap) {
        assert tasks != null && ui != null && storage != null;
        return ui.printFoundTasks(tasks.find(this.keyword));
    }

    /**
     * Checks whether an object equals this find command.
     *
     * @param obj object to be compared with this command.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof FindCommand) {
            FindCommand fc = (FindCommand) obj;
            return this.keyword.equals(fc.keyword);
        } else {
            return false;
        }
    }
}
