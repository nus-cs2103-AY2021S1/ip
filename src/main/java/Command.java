public abstract class Command {
    public abstract void execute(TaskList taskList, UI ui) throws DukeException;
}
