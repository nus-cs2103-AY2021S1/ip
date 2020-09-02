import java.util.List;

public class TaskList {
    private List<Task> taskList;

    TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return taskList.size();
    }

    public Task addTask(Task task) {
        taskList.add(task);
        return task;
    }

    public Task markTaskDone(String[] commands) throws DukeException {
        int index = Parser.getTaskIndex(commands, taskList);
        Task task = taskList.get(index);
        task.markDone();
        return task;
    }

    public Task deleteTask(String[] commands) throws DukeException {
        int index = Parser.getTaskIndex(commands, taskList);
        Task task = taskList.remove(index);
        return task;
    }

    public List<Task> getTasks() {
        return taskList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            stringBuilder.append((i+1) + "."+ taskList.get(i) +"\n");
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

}
