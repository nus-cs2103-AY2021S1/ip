public abstract class Command {
    public abstract void execute(TaskList taskList, Ui Ui, Storage storage) throws DukeException;

    public abstract Boolean isExit();
}
