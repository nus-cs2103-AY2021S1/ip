public abstract class Command {
    public abstract void execute(TaskList inputTasks, Storage storage) throws DukeException;
}
