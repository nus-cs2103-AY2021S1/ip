import java.io.IOException;

abstract class Command {

    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    abstract boolean isExit();

}
