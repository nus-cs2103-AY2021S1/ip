package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Creates an add command object.
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command.
     *
     * @param tasks   The list of existing tasks.
     * @param ui      The ui that handles user interaction.
     * @param storage The storage that stores the list of existing tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        storage.writeFile(tasks);
        return ui.showAddMessage(this.task, tasks);
    }

    /**
     * Determines if the command is an exit command.
     *
     * @return Always false.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return task.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof AddCommand)) {
            return false;
        }

        AddCommand command = (AddCommand) obj;

        return command.toString().equals(toString());
    }

}
