public class ExitCommand extends Command {

    // Attributes
    private static final String EXIT = "Bye. Hope to see you again soon!";

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        ui.showMessage(EXIT + "\n File saved!");
    }

    @Override
    boolean isExit() {
        return true;
    }
}
