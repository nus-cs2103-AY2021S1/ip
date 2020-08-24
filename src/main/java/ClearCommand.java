import java.util.ArrayList;

public class ClearCommand extends Command {

    @Override
    public void execute(TaskListHandler handler, Storage storage) {
        ArrayList<Task> taskList;
        try {
            taskList = handler.clearList();
            for (Task t1 : taskList) {
                System.out.println(t1);
            }
            storage.saveToFile(taskList);
        } catch (DukeException e){
            e.printStackTrace(System.out);
            DukeException.tryAgain();
        }
    }
}
