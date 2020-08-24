public abstract class Command {

    protected boolean completed = false;

    public abstract void execute(TaskList list, Storage storage);

    public abstract void printFeedback(Ui ui);

    public abstract boolean isExit();
}
