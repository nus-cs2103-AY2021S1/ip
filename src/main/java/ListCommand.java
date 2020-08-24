import java.util.List;

public class ListCommand extends Command {

    protected ListCommand(String args) {
        super(args);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws TaskException, StorageException {
        StringBuilder string = new StringBuilder();
        boolean isFirst = true;
        List<Task> list = taskList.getTaskList();
        for (Task task : list) {
            if (isFirst) {
                string.append(task.toString());
            } else {
                string.append("\n").append(task.toString());
            }
        }
        return string.toString();
    }
}
