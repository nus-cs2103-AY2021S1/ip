public abstract class Command {
    public boolean isExit;

    public Command() {
        isExit = false;
    }

    abstract public void execute(Ui ui, TaskList list);
}
