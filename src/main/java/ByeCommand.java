public class ByeCommand implements Command {

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws DukeException {
        ui.printExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
