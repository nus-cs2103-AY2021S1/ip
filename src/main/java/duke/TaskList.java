package duke;

import java.util.ArrayList;
import java.util.stream.Stream;

public class TaskList {
    private ArrayList<Task> tasks;
    TaskList() {
        this.tasks = new ArrayList<>();
    }
    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }
    
    public Task get(int index) {
        return tasks.get(index);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }
    
    public Stream<Task> stream() {
        return this.tasks.stream();
    }
}
