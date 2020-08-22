public class ExitCommand extends Command {
    protected boolean isExit() {
        return true;
    }

    protected void execute(String input, TaskList taskList, Storage storage) {
        return;
    }
}
