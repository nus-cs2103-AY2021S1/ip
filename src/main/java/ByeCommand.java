public class ByeCommand extends Command{

    public void execute(Ui ui, TaskManager taskManager, SaveManager saveManager) {
        ui.displayGoodbye();
        try {
            saveManager.save(taskManager);
        } catch (DukeSaveDataException e) {
            ui.displayException(e);
        }
    }

    public boolean isByeCommand() {
        return true;
    }

}
