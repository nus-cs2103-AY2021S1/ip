public abstract class Command {
    protected String fullCommand;

    Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public abstract boolean isExit();

    public abstract void executeCommand(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
