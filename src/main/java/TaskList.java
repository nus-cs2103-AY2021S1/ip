import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public Task addTask(Task task) {
        taskList.add(task);
        return task;
    }

    public Task addTodo(String args) throws TaskException {
        Todo todo = new Todo(args);
        taskList.add(todo);
        return todo;
    }

    public Task addDeadline(String args) throws TaskException {
        try {
            Deadline deadline = Deadline.create(args);
            taskList.add(deadline);
            return deadline;
        } catch (DateTimeParseException e) {
            throw new TaskException("Invalid date format");
        }
    }

    public Task addEvent(String args) throws TaskException {
        try {
            Event event = Event.create(args);
            taskList.add(event);
            return event;
        } catch (DateTimeParseException e) {
            throw new TaskException("Invalid date format");
        }
    }

    public Task completeTask(String index) throws InvalidTaskIndexException {
        try {
            int intIndex = Integer.parseInt(index) - 1;
            return completeTask(intIndex);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException("Invalid task index");
        }
    }

    public Task completeTask(int index) throws InvalidTaskIndexException {
        try {
            return taskList.get(index).completeTask();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("Invalid task index.");
        }
    }

    public Task deleteTask(String index) throws InvalidTaskIndexException {
        try {
            int intIndex = Integer.parseInt(index) - 1;
            return deleteTask(intIndex);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException("Invalid task index");
        }
    }

    public Task deleteTask(int index) throws InvalidTaskIndexException {
        try {
            return taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("Invalid task index while deleting.");
        }
    }

    public final List<Task> getTaskList() {
        return List.of(taskList.toArray(new Task[0]));
    }
}
