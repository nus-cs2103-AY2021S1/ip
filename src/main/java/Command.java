public abstract class Command {
    protected String commandName;

    public Command(String commandName) {
        this.commandName = commandName;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
