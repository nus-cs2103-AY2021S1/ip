public class ExitCommand extends Command {
    public void execute(TaskList lst, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}
