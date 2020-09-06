public class HelpCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        ui.replyHelp();
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
