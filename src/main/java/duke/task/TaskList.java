package duke.task;

import duke.Storage;

import java.util.ArrayList;

/**
 * Class the holds the tasks provided by the user.
 */
public class TaskList {
    
    private ArrayList<Task> tasks;

    /**
     * Creates a brand new task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a task list from existing data.
     * @param storage Storage object that will load the data.
     */
    public void loadFromStorage(Storage storage) {
        storage.loadData(this);
    }
    
    public void addTask(Task task) {
        tasks.add(task);
    }
    
    public void deleteTask(Task task) {
        tasks.remove(task);
    }
    
    public void markAsDone(Task task) {
        task.markDone();
    } 
    
    public Task getTask(int taskIdx) {
        return tasks.get(taskIdx - 1);
    }
    
    public int size() {
        return tasks.size();
    }
    
    @Override
    public String toString() {
        
        if (size() == 0) {
            return "No tasks added yet!";
        }
        
        StringBuilder output = new StringBuilder();
        
        for (int i = 0; i < size(); i++) {
            output.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        
        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }
}
