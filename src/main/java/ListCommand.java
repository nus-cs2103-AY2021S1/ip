import java.util.ArrayList;
import java.util.stream.Stream;

public class ListCommand implements Command {
    
    private final String[] parsedInput;
    
    public ListCommand(String[] parsedInput) {
        this.parsedInput = parsedInput;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui) {
        // involves making the ui print everything.
        ArrayList<Task> arr = tasks.getAllTasks();
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(Message.FETCHING_MSG.getMsg());
        for(Task t:arr) {
            lines.add(t.getID() + "." + t.toString());
        }
        ui.display(lines);
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
