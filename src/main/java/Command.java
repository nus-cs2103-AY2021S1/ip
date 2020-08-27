import java.io.IOException;

public abstract class Command {
    public abstract boolean isExited();

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;
}
