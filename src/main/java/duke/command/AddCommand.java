package duke.command;

import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

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
     * @throws DukeException if there is a saving issue.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTodo = tasks.addTodo(this.desc, false);
        storage.save(tasks.getList());
        ui.onAdd(newTodo, tasks.size());
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
