package juke.command.update;

import juke.command.Command;

/**
 * Represents the command to update a task.
 */
public abstract class UpdateCommand extends Command {

    protected Integer index;

    /**
     * Constructs an UpdateCommand.
     * @param index Index position of the task.
     */
    public UpdateCommand(int index) {
        super();
        this.index = index;
    }

}
