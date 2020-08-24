package duke;

abstract public class Command {
    public abstract void execute(TaskList taskItems, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
