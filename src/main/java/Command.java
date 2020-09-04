public abstract class Command {
    protected String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract void execute(TaskManager tm, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
