package duke.commands;

import duke.tasks.TaskList;

/**
 * Represents a command to be executed by Duke.
 */
public abstract class Command {

    protected TaskList taskList;

    /**
     * Constructor for {@code Command}.
     * @param taskList
     */
    public Command(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the command and gets the response.
     * @return response in String format.
     */
    public abstract String execute();

}
