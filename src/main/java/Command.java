public abstract class Command {
    public abstract void execute(Storage storage, TaskList taskList) throws DukeException;
}
