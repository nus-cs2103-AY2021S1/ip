import exceptions.DukeException;

public abstract class Command {

    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }

}
