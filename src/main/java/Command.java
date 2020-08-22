import java.util.HashMap;

public abstract class Command {

    private String command;
    private HashMap<String, String> params;
    public abstract void execute(Ui ui, TaskManager taskManager);

}
