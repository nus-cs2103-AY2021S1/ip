import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> ls;

    public TaskList(ArrayList<Task> ls) {
        this.ls = ls;
    }

    public ArrayList<Task> getLs() {
        return ls;
    }

    public boolean isEmpty() {
        return getLs().isEmpty();
    }

    public int size() {
        return getLs().size();
    }

    public Task get(int i) {
        return getLs().get(i);
    }

    public void set(int numToBeMarkedAsDone, Task task) {
        getLs().set(numToBeMarkedAsDone, task);
    }

    public void add(Task task) {
        getLs().add(task);
    }

    public void remove(int numToBeDeleted) {
        getLs().remove(numToBeDeleted);
    }
}
