package Command;

import Exception.DukeException;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

/**
 * Represents an abstract command which is inherited and is used in the Main class.
 */
public abstract class Command {
    public String[] command;

    public Command(String[] command) {
        this.command = command;
    }

    /**
     * Executes the command based on the polymorphism of each Class.
     * @param tasks the list of task saved.
     * @param ui deals with interaction with the user.
     * @param storage deals with loading tasks from the file and saving tasks in the file.
     * @throws DukeException the parents of all exception to be thrown.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicates to exit the loop or not.
     * @return boolean true or false.
     */
    public abstract boolean isExit();
}
