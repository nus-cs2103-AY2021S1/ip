public abstract class Command {
    protected abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    protected boolean isExit() {
        return false;
    }
}
