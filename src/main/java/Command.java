public abstract class Command {
    public abstract void execute(TaskList taskList, Storage storage) throws DukeException;
}
