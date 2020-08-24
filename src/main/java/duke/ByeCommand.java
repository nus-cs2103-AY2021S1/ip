package duke;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskItems, Ui ui, Storage storage) throws DukeException {
        ui.sayGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
