public abstract class Command {

    abstract public void execute(TaskList tasklist, Ui ui) throws DukeException;
}
