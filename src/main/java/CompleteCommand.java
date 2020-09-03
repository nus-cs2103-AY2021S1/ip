import java.util.ArrayList;

public class CompleteCommand extends Command {
    private ArrayList<Integer> indexArray;

    public CompleteCommand(ArrayList<Integer> indexArray) {
        this.indexArray = indexArray;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        for (int i : indexArray) {
            taskList.completeTask(i);
            storage.storeTaskList(taskList);
            ui.showTaskCompleted(i);
        }
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
