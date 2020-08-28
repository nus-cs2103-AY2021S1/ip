package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    
    public TaskList() {
        tasks = new ArrayList<>();
    }
    
    public ArrayList<Task> getList() {
        return tasks;
    }
    
    public boolean add(Task task) {
        return tasks.add(task);
    }
    
    public Task delete(int index) {
        return tasks.remove(index - 1);
    }
    
    public int size() {
        return tasks.size();
    }
    
    public Task get(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Finds matching tasks by keyword and adds them to another TaskList.
     * 
     * @param keyword Keyword used to search tasks. 
     * @return TaskList of matching tasks.
     */
    public TaskList find(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getTaskDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
