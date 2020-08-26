public class HelpCommand extends Command {

    public HelpCommand() {
        super(false);
    }
    @Override
    public void execute(TaskList taskList, UIManager uiManager, StorageManager storageManager) throws InvalidInstructionException {
        uiManager.printDukeInstructions();
    }
}
