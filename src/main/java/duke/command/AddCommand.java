package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.util.AliasMap;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents an add task command in the Duke program.
 */
public class AddCommand extends Command {

    protected String desc;

    /**
     * Initializes a newly created add task command with a description
     *
     * @param desc description of task
     */
    public AddCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the add task command.
     *
     * @param tasks TaskList of the program.
     * @param ui user interface of the program.
     * @param storage storage of the program.
     * @param aliasMap alias mapping.
     * @return the execution message.
     * @throws DukeException if there is a saving issue.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, AliasMap aliasMap) throws DukeException {
        assert tasks != null && ui != null && storage != null;
        int previousSize = tasks.getSize();
        Task newTodo = tasks.addTodo(this.desc, false);
        assert tasks.getSize() == previousSize + 1;
        storage.save(tasks.getList());
        return ui.printAddMessage(newTodo, tasks.getSize());
    }

    /**
     * Checks whether an object equals this add task command.
     *
     * @param obj object to be compared with this command.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof AddCommand) {
            AddCommand ac = (AddCommand) obj;
            return this.desc.equals(ac.desc);
        } else {
            return false;
        }
    }
}
