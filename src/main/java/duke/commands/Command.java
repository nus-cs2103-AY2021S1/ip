package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to be executed by Duke.
 */
public abstract class Command {

    protected TaskList taskList;
    protected Ui ui;

    /**
     * Constructor for {@code Command}.
     * @param taskList
     * @param ui
     */
    public Command(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Executes the command.
     */
    public abstract void execute();

}
