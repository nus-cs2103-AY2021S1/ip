public class ByeCommand extends Command{

    public ByeCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage("Bye. Hope to see you again soon!");
        storage.save(tasks);
    }
    @Override
    public boolean getIsExit() {
        return isExit;
    }
}
