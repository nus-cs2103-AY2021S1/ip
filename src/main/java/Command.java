public abstract class Command {

    abstract void execute(TaskList tasks, UI ui) throws DukeException;
}
