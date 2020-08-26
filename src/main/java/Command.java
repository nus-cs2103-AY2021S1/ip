import java.io.IOException;

public abstract class Command {
    String str;
    boolean isExit = false;

    public Command(String str) {
        this.str = str;
        isExit = false;
    }

    // different command class will have different execute implementation
    abstract public void execute(TaskList list, Ui ui, Storage storage) throws IOException;

    public boolean isExit() {
        return this.isExit;
    }
}
