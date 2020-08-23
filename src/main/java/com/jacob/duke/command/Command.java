package com.jacob.duke.command;

import com.jacob.duke.*;


public interface Command {
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException;

    public boolean isComplete();

    public boolean isBye();
}
