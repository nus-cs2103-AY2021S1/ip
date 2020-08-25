package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeCommandException;
import duke.exception.DukeStorageException;

public abstract class Command {

    public String inputCommand;


    public Command(String inputCommand) { this.inputCommand = inputCommand; }


    public abstract void execute(TaskList list, Storage storage, Ui ui) throws DukeCommandException, DukeStorageException;

    public abstract boolean isExit();
}
