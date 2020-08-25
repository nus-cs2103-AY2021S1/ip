import java.io.IOException;

public abstract class Command {
    private boolean isExit = false;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BlankTaskException, IOException;

    public boolean isExit() {
        return isExit;
    }

    public void exit() {
        isExit = true;
    }
}
