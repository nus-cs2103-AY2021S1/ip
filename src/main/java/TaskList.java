import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> ls = new ArrayList<>();

    public TaskList(ArrayList<Task> list) {
        ls.addAll(list);
    }

    /**
     * Adds param task into this.ls
     * @param task
     */
    void add(Task task) {
        ls.add(task);
    }

    /**
     * Returns size of this.ls
     * @return int
     */
    int size() {
        return ls.size();
    }

    /**
     * Marks the task at index in this.ls as done
     * @param index
     */
    void done(int index) {
        ls.get(index).done();
    }

    /**
     * Removes the task at index in this.ls
     * @param index
     */
    void remove(int index) {
        ls.remove(index);
    }

    /**
     * Returns task at index in this.ls
     * @param index
     * @return Task
     */
    Task get(int index) {
        return ls.get(index);
    }

    String find(String match) {
        String s = "";
        for(Task task : ls) {
            if(task.toString().contains(match)) {
                s = s.concat(task.toString());
                s = s.concat("\n");
            }
        }
        return s;
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
