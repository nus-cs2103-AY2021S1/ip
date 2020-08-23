public class UnknownCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(new UnknownCommandException());
    }
}
