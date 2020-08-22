public abstract class Command {
    protected abstract boolean isExit();

    protected abstract void execute(String input, TaskList taskList, Storage storage)
            throws DukeException;
}
