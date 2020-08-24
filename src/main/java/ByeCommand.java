public class ByeCommand extends Command{

    private Ui ui;

    public ByeCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public void execute() {
        this.ui.showExit();
    }
}
