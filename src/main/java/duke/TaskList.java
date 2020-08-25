package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
    
    public void addTask(Task task) {
        taskList.add(task);
    }
    
    public Task removeTask(int index) {
        // 1 index list
        return taskList.remove(index - 1);
    }
    
    public List<Task> getAll() {
        return this.taskList;
    }
    
    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }
    
    public int getSize() {
       return taskList.size();
    }
    
    public void clearAll() {
        taskList.clear();
    }
    
    @Override
    public String toString() {
            String formattedListString = "";
            for (int i = 0; i < taskList.size(); i ++) {
                formattedListString+= String.format("%d. %s\n", i + 1, taskList.get(i));
            }
            return formattedListString;
    }
    
}
