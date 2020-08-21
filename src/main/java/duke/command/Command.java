abstract public class Command {

    protected boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }
    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
