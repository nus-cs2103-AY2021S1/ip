public class PrintListCommand implements Command {

    private boolean isDone;

    PrintListCommand() {
        this.isDone = false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CartonaException {
        if (isDone) {
            throw new CartonaException("Error: PrintListCommand already executed");
        }
        ui.printTaskList(taskList);
        this.isDone = true;
    }

    @Override
    public boolean isExitCmd() {
        return false;
    }
}
