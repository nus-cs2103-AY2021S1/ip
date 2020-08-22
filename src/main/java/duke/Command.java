package duke;

public abstract class Command {

    public boolean isExit() { return false; }

    public abstract void execute(TaskList list, Ui ui, Storage storage) throws DukeException;
}
