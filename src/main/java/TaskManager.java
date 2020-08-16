import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TaskManager {
    List<Task> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    public Todo addTodo(String content) throws DukeException {
        Todo todo = new Todo(content);
        taskList.add(todo);
        return todo;
    }

    public Deadline addDeadline(String content, String datetimeDue) throws DukeException {
        Deadline deadline = new Deadline(content, datetimeDue);
        taskList.add(deadline);
        return deadline;
    }

    public Event addEvent(String content, String datetime) throws DukeException {
        Event event = new Event(content, datetime);
        taskList.add(event);
        return event;
    }

    public Task deleteTask(int taskNumber) throws DukeException {
        int index = getIndex(taskNumber);
        return taskList.remove(index);
    }

    public Task completeTask(int taskNumber) throws DukeException {
        int index = getIndex(taskNumber);
        Task task = taskList.get(index);
        task.setComplete(true);
        return task;
    }

    private int getIndex(int taskNumber) throws DukeException {
        if (taskNumber <= 0) {
            throw new DukeException("Invalid task number specified, task number must be greater than 0.");
        } else if (taskNumber > taskList.size()) {
            throw new DukeException("Task number specified is larger than current amount of tasks.");
        }
        int index = taskNumber - 1;
        return index;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return IntStream.range(0, taskList.size())
                .mapToObj(i -> String.format("%d. %s", i + 1, taskList.get(i).toString().concat("\n")))
                .reduce("", String::concat);
    }

    public int getTaskCount() {
        return taskList.size();
    }
}
