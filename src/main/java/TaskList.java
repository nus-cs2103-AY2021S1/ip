import java.util.List;

public class TaskList {
    private List<Task> tasks;

    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public Task markTaskDone(String[] commands) throws DukeException {
        int index = Parser.getTaskIndex(commands, tasks);
        Task task = tasks.get(index);
        task.markDone();
        return task;
    }

    public Task deleteTask(String[] commands) throws DukeException {
        int index = Parser.getTaskIndex(commands, tasks);
        Task task = tasks.remove(index);
        return task;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append((i+1) + "."+ tasks.get(i) +"\n");
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

}
