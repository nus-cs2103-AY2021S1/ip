public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException;
}