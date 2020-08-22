package duke;

public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.printBye();
    }
}
