import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int taskNo) throws DukeException {
        if (taskNo <= 0 || taskNo > tasks.size()) {
            throw new DukeException("Task " + taskNo + " does not exist.");
        }

        return tasks.get(taskNo - 1);
    }

    public Task doTask(int taskNo) throws DukeException {
        Task task = getTask(taskNo);

        task.markAsDone();
        return task;
    }

    public Task deleteTask(int taskNo) throws DukeException {
        Task task = getTask(taskNo);

        tasks.remove(task);
        return task;
    }

    public List<Task> getDueTasks(LocalDate date) {
        return tasks.stream().filter(task -> task.isDue(date)).collect(Collectors.toList());
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
