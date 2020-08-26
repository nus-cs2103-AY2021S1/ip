package duke;

public class ShowListCommand extends Command {
    ShowListCommand(String command) {
        super(command);
    }
    public void execute(TaskList list, Ui ui, Storage saveData) {
        ui.showList(list);
    }

    public boolean isExit() {
        return false;
    }
}
