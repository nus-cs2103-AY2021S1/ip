public class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
