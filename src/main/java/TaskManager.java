import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TaskManager {
    List<Task> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    public void addTodo(String content) {
        Todo todo = new Todo(content);
        taskList.add(todo);
    }

    public void addDeadline(String content, String datetimeDue) {
        Deadline deadline = new Deadline(content, datetimeDue);
        taskList.add(deadline);
    }

    public void addEvent(String content, String datetime) {
        Event event = new Event(content, datetime);
        taskList.add(event);
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
