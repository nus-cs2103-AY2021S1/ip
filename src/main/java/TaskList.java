import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> tasks;

    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    List<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(String task, String date, TaskType taskType) throws DukeException {

        Task t;
        switch (taskType) {
            case TODO: {
                t = new TodoTask(task);
                tasks.add(t);
                break;
            }
            case DEADLINE: {
                t = new DeadlineTask(task, date);
                tasks.add(t);
                break;
            }
            case EVENT: {
                t = new EventTask(task, date);
                tasks.add(t);
                break;
            }
            default:
                throw new DukeException("Invalid Task Type");
        }
    }

    void deleteTask (int index) throws InvalidIndexException {
        if (index > tasks.size() || index < 1) {
            throw new InvalidIndexException("☹ OOPS!!! There is no such task.");
        }
        tasks.remove(index - 1);
    }

    void completeTask(int index) throws InvalidIndexException {
        if (index > tasks.size() || index < 1) {
            throw new InvalidIndexException("☹ OOPS!!! There is no such task.");
        }
        Task completedTask = tasks.get(index - 1);
        completedTask.markAsDone();
    }

    int size() {
        return tasks.size();
    }

    Task get(int i) {
        return tasks.get(i);
    }

    @Override
    public String toString() {
        return "Now you have " + tasks.size() + " tasks in the list.";
    }
}
