package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command Class
 */
public abstract class Command {
    public final String description;

    Command(String description){
        this.description = description;
    }

    /**
     * Returns true if command ends the program
     *
     * @return true if command ends the program
     */
    public abstract boolean isComplete();

    /**
     * Executes the command
     * @param taskList
     * @param ui
     * @param storage
     * @return String stating what the command has done
     * @throws DukeException
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
