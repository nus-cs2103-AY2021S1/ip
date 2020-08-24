public class Command {
    protected boolean isExit;
    private int itemIndex;

    public Command() {
        this.isExit = false;
        this.itemIndex = -1;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void execute(Ui ui, Storage listStorage, TaskList taskList) {
    }
}
