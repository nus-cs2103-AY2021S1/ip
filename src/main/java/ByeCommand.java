public class ByeCommand extends Command {
    

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.storelist(taskList);
        ui.farewell();
        
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
