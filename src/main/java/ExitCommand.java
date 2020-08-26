public class ExitCommand extends Command{
    public ExitCommand(String command) {
        super(command, true);
    }

    protected void execute(TaskList list, Ui ui, Storage storage) {
        ui.outro();
    }
}
