package duke.task;

import duke.Storage;

import java.util.ArrayList;

/**
 * Class the holds the tasks provided by the user.
 */
public class TaskList {
    
    private ArrayList<Task> taskList;

    /**
     * Creates a brand new task list.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Creates a task list from existing data.
     * @param storage Storage object that will load the data.
     */
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

    /**
     * Finds and list tasks with description containing the keyword.
     * @param keyword Keyword for the search.
     * @return
     */
    public String listTasksWithKeyword(String keyword) {
        StringBuilder output = new StringBuilder();
        int i = 1;
        
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                output.append(i).append(". ").append(task).append("\n");
            }
        }
        
        output.deleteCharAt(output.length() - 1);
        return output.toString();
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
