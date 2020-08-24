public class UnknownCommand implements Command {

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(new DukeUnknownInputException());
        return true;
    }
}
