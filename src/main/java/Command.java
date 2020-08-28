public abstract class Command {

    public abstract void execute(TaskList tasks, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
