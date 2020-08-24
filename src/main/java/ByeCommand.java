public class ByeCommand extends Command {
    public ByeCommand () {
        super();
        this.isExit = true;
    }

    @Override
    public void execute(Ui ui, Storage listStorage, TaskList taskList) {
        ui.goodBye();
    }
}
