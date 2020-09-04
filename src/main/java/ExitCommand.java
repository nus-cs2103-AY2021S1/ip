import java.io.IOException;
import java.util.ArrayList;

public class ExitCommand extends Command{

    public ExitCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskManager tm, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> taskList = tm.getTaskList();
        try {
            storage.save(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
