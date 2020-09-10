import java.util.ArrayList;

public class DeleteCommand extends Command {
    
    private final ArrayList<Integer> taskIndexes;

    DeleteCommand(ArrayList<Integer> taskIndexes) {
        this.taskIndexes = taskIndexes;
    }

    @Override
    String  execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> tasks = taskList.deleteTasks(taskIndexes);
        storage.updateTasks(taskList);
        return ui.showDeleteTasks(tasks, taskList.getListLength());
    }
}
