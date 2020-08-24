package commands;

import data.exception.DukeException;

public abstract class Command {

    public abstract void execute() throws DukeException;
}
