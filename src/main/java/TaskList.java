import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this(new ArrayList<Task>());
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index){
         return getTasks().get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void completeTask(int index) {
        Task newTask = tasks.get(index).markAsDone();
        tasks.set(index, newTask);
    }

}
