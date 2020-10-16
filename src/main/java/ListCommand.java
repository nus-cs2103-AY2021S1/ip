import java.io.FileNotFoundException;

public class ListCommand extends Command {
    public String commandWord = "list";
    public TaskList taskList;
    public String filePath;

    public ListCommand(TaskList taskList, String filePath) {
        super("list");
        this.taskList = taskList;
        this.filePath = filePath;
    }

    @Override
    public String execute() {
        if (taskList.list.isEmpty()) {
            return Ui.emptyList();
        } else {
            Storage storage = new Storage(filePath);
            TaskList tasks;
            String s = "";
            try {
                tasks = new TaskList(storage.load());
                s = Ui.returnAllTasks(tasks);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return s;
        }
    }
}
