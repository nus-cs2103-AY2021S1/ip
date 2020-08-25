package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    
    private final ArrayList<Task> taskList;
    
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    
    public TaskList (ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    
    public void addTask(Task t) {
        taskList.add(t);
    }
    
    public void removeTask (int index) throws IndexOutOfBoundsException{
        taskList.remove (taskList.get(index));
    }
    
    public void setDone (int index) {
        taskList.get(index).markDone();
    }

    public int size() {
        return taskList.size();
    }
    
    public Task get(int index) throws IndexOutOfBoundsException {
        return taskList.get(index);
    }
    
    public TaskList filter(String s) {
        TaskList filter = new TaskList();
        
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getDescription().contains(s)) {
                filter.addTask(this.get(i));
            }
        }
        return filter;
    }
    
}
