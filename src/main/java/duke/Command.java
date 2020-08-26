package duke;

public abstract class Command {

    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
