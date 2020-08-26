public class ErrorCommand extends Command {
    private String errorMessage;

    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidInputException {
        ui.displayError(errorMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}


