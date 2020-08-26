import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getList() {
        return this.tasks;
    }

    public int getLength() {
        return this.tasks.size();
    }

    Task createTask(String taskType, String desc) throws MissingDeadlineException{
        if (taskType.equals("todo")) {
            return new Todo(desc);
        } else if (taskType.equals("deadline")) {
            return new Deadline(desc);
        } else {
            return new Event(desc);
        }
    }

    public Task addTask(String taskType, String desc) throws MissingDeadlineException {
        Task task = createTask(taskType, desc);
        this.tasks.add(task);
        return task;
    }

    String formatTask(int num, List<Task> tasks) {
        String lineBreak = num != tasks.size() - 1 ? "\n  " : "";
        return (num + 1) + "." + tasks.get(num) + lineBreak;
    }

    String formattedList() {
        String list = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            list += formatTask(i, this.tasks);
        }
        return list;
    }

    Task completeTask(int num) throws MissingTaskException {
        if (num > 0 && num <= tasks.size()) {
            Task task = tasks.get(num - 1);
            task.completeTask();
            return task;
        } else {
            throw new MissingTaskException(num);
        }
    }

    Task deleteTask(int num) throws MissingTaskException {
        if (num > 0 && num <= tasks.size()) {
            Task task = tasks.remove(num - 1);
            return task;
        } else {
            throw new MissingTaskException(num);
        }
    }

    String findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.match(keyword)) {
                matchingTasks.add(task);
            }
        }

        String list = "";
        for (int i = 0; i < matchingTasks.size(); i++) {
            list += formatTask(i, matchingTasks);
        }
        return list;
    }
}
