package duke;

import java.util.ArrayList;
import duke.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;
    
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }
    
    public void add(Task task) {
        tasks.add(task);
    }
    
    public void delete(int idx) {
        tasks.remove(idx);
    }
    
    public Task get(int idx) {
        return tasks.get(idx);
    }
    
    public Task[] getArray() {
        return this.tasks.toArray(new Task[0]);
    }
    
    public ArrayList<Task> filter(String keyword) {
        ArrayList<Task> satisfiedTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.contains(keyword)) {
                satisfiedTasks.add(tasks.get(i));
            }
        }
        return satisfiedTasks;
    }
    
    public String getList() {
        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            list += t.writeMessage() + "\n";
        }
        return list;
    }
    
    public int size() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            str += (i + 1) + ": " + tasks.get(i);

            if (i != tasks.size() - 1) {
                str += "\n";
            }
        }

        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof TaskList)) {
            return false;
        }

        TaskList tasks = (TaskList) obj;

        return tasks.toString().equals(toString());
    }
    
}
