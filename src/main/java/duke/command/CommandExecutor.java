package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Interface for any class that will delegate the parsing and the execution of user inputs.
 * Contains a shouldExit method which only returns true if the "bye" command was issued by the user.
 */
public interface CommandExecutor {
    public String execute(String in, TaskList taskList) throws DukeException;

    public void loadSaveString(String in, TaskList taskList) throws DukeException;

    public boolean shouldExit();
}
