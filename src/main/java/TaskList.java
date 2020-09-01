import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public String markAsDone(int value) throws IndexOutOfBoundsException {
        String message = "";
        try {
            message = this.tasks.get(value).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            e.getMessage();
        }
        return message;
    }

    public void deleteTask(int value) throws IndexOutOfBoundsException {
        try {
            this.tasks.remove(value);
        } catch (IndexOutOfBoundsException e) {
            e.getMessage();
        }
    }

    public int numOfTasks() {
        return this.tasks.size();
    }

    public Task getTask(int value) {
        return this.tasks.get(value);
    }


}
