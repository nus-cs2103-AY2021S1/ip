package duke.command;

import duke.exception.DukeException;

public abstract class Command {
    String[] args;
    
    public abstract String execute() throws DukeException;
}
