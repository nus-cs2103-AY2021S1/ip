import java.io.IOException;

public abstract class Command {
    protected abstract boolean isExit();

    protected abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException;
}
