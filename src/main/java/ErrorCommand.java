public class ErrorCommand extends Command {
    final String errorMessage;
    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Prints out an error message.
     * @param ui a Ui instance to enable calling of Ui functions
     * @param storage a Storage instance to enable calling of Storage functions
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        return ui.taskPrint(errorMessage);
    }
}
