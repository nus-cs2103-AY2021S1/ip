public abstract class Command {
    boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean getIsExit();
}
