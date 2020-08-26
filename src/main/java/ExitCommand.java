public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidInputException {
        ui.displayExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}



