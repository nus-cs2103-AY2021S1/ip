public class HelpCommand extends Command {

    public HelpCommand() {
        super(false);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.printHelp();
    }
}
