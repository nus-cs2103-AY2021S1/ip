public class ExitCommand implements Command {

    private boolean isDone;

    ExitCommand() {
        this.isDone = false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CartonaException {
        if (isDone) {
            throw new CartonaException("Error: PrintErrorCommand already executed");
        }
        ui.printExitMessage();
        this.isDone = true;
    }

    @Override
    public boolean isExitCmd() {
        return true;
    }
}