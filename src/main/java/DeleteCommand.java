import java.util.ArrayList;
import java.util.List;

public class DeleteCommand implements Command {
    private final int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws DukeException {
        Task delTask = tasks.delete(taskNum);
        ui.printWithWrapper(new ArrayList<>(List.of(
                "OK! I have deleted the following task for your list:",
                delTask.toString(),
                tasks.getListStatus())), false, false);
    }
}
