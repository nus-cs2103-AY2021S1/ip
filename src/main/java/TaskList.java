import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return taskList;
    }

    public int getCount() {
        return taskList.size();
    }

    public Task completeTask(int index) throws IndexOutOfBoundsException {
        taskList.get(index - 1).markAsDone();
        return taskList.get(index - 1);
    }

    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        return taskList.remove(index - 1);
    }

    public Task addTodo(String name) {
        Todo todo = new Todo(name, false);
        addTask(todo);
        return todo;
    }

    public Task addDeadline(String name, Date by) {
        Deadline deadline = new Deadline(name, false, by);
        addTask(deadline);
        return deadline;
    }

    public Task addEvent(String name, Date at) {
        Event event = new Event(name, false, at);
        addTask(event);
        return event;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public List<Task> getTasksWithDate(Date date) {
        List<Task> result = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getType() != TaskType.TODO && task.getDate().equals(date)) {
                result.add(task);
            }
        }
        return result;
    }
}
