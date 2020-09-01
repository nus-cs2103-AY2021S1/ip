public abstract class Command {
    public abstract String execute(TaskList taskList, UI ui) throws DukeException;
}
