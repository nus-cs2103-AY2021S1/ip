import java.util.List;

public class Manager {

    private final List<Task> tasks;

    protected Manager(List<Task> tasks) {
        this.tasks = tasks;
    }

    protected void manageInput(String keyWord, String restOfWord) {
        try {
            TaskManager taskManager = new TaskManager(tasks, keyWord, restOfWord);
            if (keyWord.equals("list")) {
                showList(tasks);
            } else if (keyWord.equals("done")) {
                taskManager.handleDone();
            } else if (keyWord.equals("delete")) {
                taskManager.handleDeletion();
            } else {
                if (keyWord.equals("todo") || keyWord.equals("deadline") || keyWord.equals("event")) {
                    taskManager.addTask();
                } else {
                    throw new UnknownCommandException();
                }
            }
        } catch (DukeException e) {
            Ui.printMsg(e.getMessage());
        }
    }

    private void showList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            Ui.emptyTaskList();
        } else {
            Ui.showTaskList(tasks);
        }
    }
}
