public abstract class Command {
    public boolean isExit;
    private final String input;

    public Command(String input) {
        isExit = false;
        this.input = input;
    }

    abstract public void execute(Ui ui, TaskList list, Storage storage);
}
