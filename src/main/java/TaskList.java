import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task markTaskAsDone(int index) throws NoSuchTaskException {
        try {
            Task task = tasks.get(index);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException();
        }
    }

    public Task deleteTask(int index) throws NoSuchTaskException {
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException();
        }
    }

    public int numOfTasks() {
        return tasks.size();
    }

    // temp
    public String tasksRemaining() {
        return String.format("Now you have %d tasks in the list", numOfTasks());
    }

}