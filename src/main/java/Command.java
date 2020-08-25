public abstract class Command {
    protected boolean exit;

    public boolean isExit() {
        return this.exit;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
