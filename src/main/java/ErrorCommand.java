public class ErrorCommand extends Command{
    public String errorMessage;
    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(Ui ui, Storage storage) {
        ui.taskPrint(errorMessage);
    }
}
