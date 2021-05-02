package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;

public interface Command {
    /**
     * Executes a given command.
     *
     * @param tasks  TaskList to be manipulated.
     * @param store  Storage object to manage saving of data.
     * @throws DukeException
     */
    public String execute(TaskList tasks, Storage store) throws DukeException;

    /**
     * Return true if command signals the termination of the chatbot.
     *
     * @return  True if command terminates Dook and false if otherwise.
     */
    public boolean isExit();
}
