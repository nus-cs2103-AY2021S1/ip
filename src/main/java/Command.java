public abstract class Command {
    public final boolean isExit;

    public Command() {
        isExit = false;
    }

    abstract public void execute();
}
