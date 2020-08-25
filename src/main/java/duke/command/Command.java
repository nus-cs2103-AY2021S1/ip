package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public abstract class Command {
    private boolean shouldLoop;

    public Command(boolean shouldLoop) {
        this.shouldLoop = shouldLoop;
    }
    
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    
    public boolean shouldLoop() {
        return this.shouldLoop;
    }
}