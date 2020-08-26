import java.io.IOException;

public abstract class Command {

    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean getExitStatus() {
        return isExit;
    }

    public abstract void execute(TaskList taskList, UIManager uiManager, StorageManager storageManager)
            throws InvalidInstructionException, IOException;
}
