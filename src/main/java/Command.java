import java.io.IOException;

public abstract class Command {
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException { }

    public boolean isExit() {
        return false;
    }
}
