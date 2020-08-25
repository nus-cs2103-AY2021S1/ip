package duke.util;

import duke.task.Task;

import java.util.ArrayList;
import java.util.StringJoiner;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    public void add(Task task) {
        tasks.add(task);
    }
    
    public Task remove(int index) {
        return tasks.remove(index);
    }
    
    public int size() {
        return tasks.size();
    }
    
    public Task get(int index) {
        return tasks.get(index);
    }
    
    public String getSaveFormat() {
        StringJoiner text = new StringJoiner("\n");
        for (Task task : tasks) {
            text.add(task.toSaveFormat());
        }
        return text.toString();
    }
    
    public String find(String toFind) {
        StringJoiner response = new StringJoiner("\n");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).contains(toFind)) {
                response.add(String.format("%d.%s", i+1, tasks.get(i)));
            }
        }
        return response.toString();
    }
}
