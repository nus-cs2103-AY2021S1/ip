package shiro.command;

import shiro.storage.Storage;
import shiro.task.TaskList;
import shiro.exception.ShiroException;

/**
 * a class representing commands. it evaluates the commands given by the user and responds appropriately
 */
public abstract class Command {
    protected String fullCommand;

    /**
     * class constructor
     * @param fullCommand a string representing the full command given by the user
     */
    Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * performs the appropriate command based on the type of command it is
     * @param tasks the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return the command result based on the type of command given
     * @throws ShiroException if there any issues executing the command
     */
    public abstract CommandResult execute(TaskList tasks, Storage storage) throws ShiroException;
}
