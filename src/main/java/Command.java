public abstract class Command {

    protected boolean isExit() { return false; }

    protected abstract void execute(TaskList list, Ui ui, Storage storage) throws DukeException;
}
