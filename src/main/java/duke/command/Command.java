package duke.command;

import duke.TaskList;
import duke.exception.DukeException;
import duke.ui.Ui;

import java.io.IOException;

/**
 * A command can be executed on and can be an Exit Command
 */
public interface Command {
    
    void execute(TaskList tasks, Ui ui) throws DukeException, IOException;
    
    boolean isExit();
    
}
