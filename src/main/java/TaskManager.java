import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TaskManager {
    TaskStorage taskStorage;

    public TaskManager() throws IOException, DukeException {
        this.taskStorage = TaskStorage.init();
    }

    public Todo addTodo(String content) throws DukeException, IOException {
        Todo todo = new Todo(content);
        taskStorage.addTask(todo);
        return todo;
    }

    public Deadline addDeadline(String content, String datetimeDue) throws DukeException, IOException {
        Deadline deadline = new Deadline(content, datetimeDue);
        taskStorage.addTask(deadline);
        return deadline;
    }

    public Event addEvent(String content, String datetime) throws DukeException, IOException {
        Event event = new Event(content, datetime);
        taskStorage.addTask(event);
        return event;
    }

    public Task deleteTask(int taskNumber) throws DukeException, IOException {
        int index = getIndex(taskNumber);
        return taskStorage.removeTask(index);
    }

    public Task completeTask(int taskNumber) throws DukeException, IOException {
        int index = getIndex(taskNumber);
        Task task = taskStorage.getTask(index);
        task.setComplete(true);
        taskStorage.save();
        return task;
    }

    private int getIndex(int taskNumber) throws DukeException {
        if (taskNumber <= 0) {
            throw new DukeException("Invalid task number specified, task number must be greater than 0.");
        } else if (taskNumber > taskStorage.getTaskCount()) {
            throw new DukeException("Task number specified is larger than current amount of tasks.");
        }
        int index = taskNumber - 1;
        return index;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return IntStream.range(0, taskStorage.getTaskCount())
                .mapToObj(i -> String.format("%d. %s", i + 1, taskStorage.taskList.get(i).toString().concat("\n")))
                .reduce("", String::concat);
    }

    public int getTaskCount() {
        return taskStorage.getTaskCount();
    }
}
