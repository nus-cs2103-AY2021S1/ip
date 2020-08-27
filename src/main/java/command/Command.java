package command;

import exception.DukeException;

import storage.Storage;

import tasklist.TaskList;

import ui.Ui;


public abstract class Command {
    public Command(){}

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
