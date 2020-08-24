package command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;

/**
 * Parent Abstract Class to be inherited by actual children classes
 */

public abstract class Command {
    public CommandType type;

    /**
     * Runs the command, modifying the contents of taskList, Ui or storage depending on the implementation
     * of child class
     * @param taskList ArrayList of Tasks Objects
     * @param ui Object of the Ui class
     * @param storage Object of the Storage class
     * @throws DukeException Exception that occurs while executing the command
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return this.type.equals(CommandType.Bye);
    }

    public enum CommandType {
        Bye, Todo, Deadline, Event, Done, Delete, List, Find
    }

    Command(CommandType type) {
        this.type = type;
    }
}
