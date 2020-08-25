import java.util.ArrayList;

public class DoneCommand implements Command {
    private final String[] parsedInput;
    
    protected DoneCommand(String[] parsedInput) {
        this.parsedInput = parsedInput;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(Message.DONE_MSG.getMsg());
        lines.add(tasks.completeTask(Integer.parseInt(this.parsedInput[1])));
        ui.display(lines);
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
