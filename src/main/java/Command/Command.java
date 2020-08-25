package Command;
import Task.TaskList;
import ParserStorageUi.Ui;
import ParserStorageUi.Storage;
import Exceptions.*;

abstract public class Command {

    protected final String command;

    public Command(String command){
        this.command = command;
    }

    /** Executes the command **/
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /** Check if the current command is an exit command **/
    public abstract boolean isExit();

}
