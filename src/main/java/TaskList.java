import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> ls = new ArrayList<>();

    public TaskList(ArrayList<Task> list) {
        ls.addAll(list);
    }

    void add(Task task) {
        ls.add(task);
    }

    int size() {
        return ls.size();
    }

    void done(int index) {
        ls.get(index).done();
    }

    void remove(int index) {
        ls.remove(index);
    }

    Task get(int index) {
        return ls.get(index);
    }

    @Override
    public String toString() {
        String s = "";
        for(Task task : ls) {
            s = s.concat(task.toString());
            s = s.concat("\n");
        }
        return s;
    }
}
