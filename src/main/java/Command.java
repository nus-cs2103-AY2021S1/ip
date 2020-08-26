abstract class Command {
    private String action;
    protected boolean isExit;

    public Command(String action) {
        this.action = action;
        isExit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws CommandException;

    public boolean isExit() {
        return isExit;
    }

    public void setExit() {
        isExit = true;
    }
}
