import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class TaskList {
    List<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    public TaskList(List<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public List<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    public List<Task> getListOfTasks(LocalDate date) {
        return this.listOfTasks.stream().filter(task -> (task instanceof Event && ((Event) task).isOnDate(date))
                || (task instanceof Deadline && ((Deadline) task).isOnDate(date))).collect(Collectors.toList());
    }

    public Task markTaskDone(int idx) throws DukeException {
        if (idx < 0 || idx >= listOfTasks.size()) {
            throw new DukeException("The task cannot be found.");
        }
        Task task = listOfTasks.get(idx);
        task.markAsDone();
        return task;
    }

    public Task deleteTask(int idx) throws DukeException {
        if (idx < 0 || idx >= listOfTasks.size()) {
            throw new DukeException("The task cannot be found.");
        }
        Task task = listOfTasks.get(idx);
        listOfTasks.remove(idx);
        return task;
    }

    public void addTask(Task task) {
        this.listOfTasks.add(task);
    }

    public int size() {
        return this.listOfTasks.size();
    }
}
