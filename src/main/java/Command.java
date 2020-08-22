import java.util.HashMap;

public abstract class Command {

    public abstract void execute(Ui ui, TaskManager taskManager, SaveManager saveManager);
    public abstract boolean isByeCommand();

}
