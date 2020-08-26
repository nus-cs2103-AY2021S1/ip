public abstract class Command {
    protected final String command;
    public final boolean isExit;

    public Command(String command, boolean isExit) {
        this.command = command;
        this.isExit = isExit;
    }

    protected abstract void execute(TaskList list, Ui ui, Storage storage);
}
