import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TaskManager {
    List<Task> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(String content) {
        Task task = new Task(content);
        taskList.add(task);
    }

    public void completeTask(int taskNumber) {
        if (taskNumber <= 0) {
            throw new IllegalArgumentException("Invalid task number specified");
        } else if (taskNumber > taskList.size()) {
            throw new IllegalArgumentException("Task number specified is larger than current amount of tasks");
        }
        int index = taskNumber - 1;
        Task task = taskList.get(index);
        task.setComplete(true);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return IntStream.range(0, taskList.size())
                .mapToObj(i -> String.format("%d. %s", i + 1, taskList.get(i).toString().concat("\n")))
                .reduce("", String::concat);
    }

}
