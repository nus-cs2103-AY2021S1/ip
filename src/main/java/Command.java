public abstract class Command {

    abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    abstract boolean isExit();

}
