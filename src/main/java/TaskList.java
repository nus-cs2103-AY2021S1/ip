import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void add(Task newTask) {
        this.list.add(newTask);
    }

    public Task get(int userIndex) throws IllegalArgumentException {
        try {
            int index = userIndex - 1;
            return list.get(index);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }

    }

    public void remove(int userIndex) throws IllegalArgumentException {
        try {
            int index = userIndex - 1;
            this.list.remove(index);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public int getSize() {
        return this.list.size();
    }
}
