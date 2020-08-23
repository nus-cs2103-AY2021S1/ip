public class ByeCommand extends Command {
    protected boolean isExit;

    public void runCommand(TaskList taskList, Ui ui, Storage storage) {
        // Exit program.
    }

    public boolean exitCheck() {
        isExit = true;
        return isExit;
    }
}