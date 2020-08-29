public class InvalidCommand extends Command {
    private String error;
    
    InvalidCommand(String error) {
        this.error = error;
    }

    @Override
    void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.printStatus(error);
    }
}
