package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.Ui;

public interface Command {
    /**
     * Executes a given command.
     *
     * @param tasks  TaskList to be manipulated.
     * @param ui  Ui Object to handle user interactions.
     * @param store  Storage object to manage saving of data.
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException;

    /**
     * Return true if command signals the termination of the chatbot.
     *
     * @return  True if command terminates Dook and false if otherwise.
     */
    public boolean isExit();
}
