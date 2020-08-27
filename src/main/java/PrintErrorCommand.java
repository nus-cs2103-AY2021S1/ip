public class PrintErrorCommand implements Command {

    private boolean isDone;
    private String errorMessage;

    PrintErrorCommand(String errorMessage) {
        this.isDone = false;
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CartonaException {
        if (isDone) {
            throw new CartonaException("Error: PrintErrorCommand already executed");
        }
        ui.printErrorMessage(errorMessage);
        this.isDone = true;
    }

    @Override
    public boolean isExitCmd() {
        return false;
    }
}

