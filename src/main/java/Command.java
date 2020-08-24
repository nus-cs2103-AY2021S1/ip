import java.io.IOException;

public abstract class Command {
    boolean isBye = false;
    public abstract void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException;
}
