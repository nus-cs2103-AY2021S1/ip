public class ListCommand extends Command{

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(tasks.toString());
    }

    @Override
    public boolean getIsExit() {
        return isExit;
    }
}
