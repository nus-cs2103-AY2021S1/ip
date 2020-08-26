package command;

import exception.DukeException;
import logic.Storage;
import logic.Ui;
import tasks.TaskList;

/**
 * Abstract class of all the commands that is created
 * after parsing the user's input
 */

public abstract class Command {

    public final String input;

    public Command(String input) {
        this.input = input;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
