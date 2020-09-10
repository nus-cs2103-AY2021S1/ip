import java.util.ArrayList;

public class DoneCommand extends Command {

    private final ArrayList<Integer> taskIndexes;

    DoneCommand(ArrayList<Integer> taskIndexes) {
        this.taskIndexes = taskIndexes;
    }

    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> tasks = taskList.doneTasks(taskIndexes);
        storage.updateTasks(taskList);
        return ui.showDoneTasks(tasks);
    }
}
