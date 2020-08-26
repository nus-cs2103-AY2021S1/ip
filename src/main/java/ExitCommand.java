import java.io.IOException;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList taskList, UIManager uiManager, StorageManager storageManager)
            throws IOException {
        storageManager.saveData(taskList.getTaskList());
    }
}
