package duke.task;

import duke.Storage;

import java.util.ArrayList;

public class TaskList {
    
    private ArrayList<Task> taskList;
    
    public TaskList() {
        taskList = new ArrayList<>();
    }
    
    public void loadFromStorage(Storage storage) {
        storage.loadData(this);
    }
    
    public void addTask(Task task) {
        taskList.add(task);
    }
    
    public void deleteTask(Task task) {
        taskList.remove(task);
    }
    
    public void markAsDone(Task task) {
        task.markDone();
    } 
    
    public Task getTask(int taskIdx) {
        return taskList.get(taskIdx - 1);
    }
    
    public int size() {
        return taskList.size();
    }
    
    @Override
    public String toString() {
        
        if (size() == 0) {
            return "No tasks added yet!";
        }
        
        StringBuilder output = new StringBuilder();
        
        for (int i = 0; i < size(); i++) {
            output.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
        }
        
        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }
}
