public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    @Override
    public void execute(Tasklist tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
