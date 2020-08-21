public class ByeCommand extends Command {
    public ByeCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
        System.exit(0);
    }
}
