public abstract class Command {
    public abstract void execute(TaskList list, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
