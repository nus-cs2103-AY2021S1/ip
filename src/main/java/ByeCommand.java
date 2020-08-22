public class ByeCommand extends Command {
    boolean exitCheck;

    public void runCommand(TaskList taskList, Ui ui, Storage storage) {
        // Exit program.
    }

    public boolean exitCheck() {
        exitCheck = true;
        return exitCheck;
    }
}