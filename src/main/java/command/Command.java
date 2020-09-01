package command;

import duke.TaskList;
import duke.Ui;
import exception.DukeException;

public abstract class Command {
    public abstract String execute(String inputMsg, TaskList currList, Ui ui) throws DukeException;
}
