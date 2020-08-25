package duke.command;

import duke.TaskList;
import duke.exception.DukeException;
import duke.ui.Ui;

public interface Command {
    
    void execute(TaskList tasks, Ui ui) throws DukeException;
    
    boolean isExit();
    
}
