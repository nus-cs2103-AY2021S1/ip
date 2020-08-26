package duke;

public class ExitCommand extends Command {
    ExitCommand(String command) {
        super(command);
    }
    public void execute(TaskList list, Ui ui, Storage saveData) {
        ui.byeMessage();
    }

    public boolean isExit() {
        return true;
    }
}
