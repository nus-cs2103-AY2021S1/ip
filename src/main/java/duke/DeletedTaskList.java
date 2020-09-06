package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class DeletedTaskList {
    
    private ArrayList<Task> deletedTaskList;
    
    public DeletedTaskList() {
        this.deletedTaskList = new ArrayList<>();
    }
    
    public void addDeletedTask (Task deletedTask) {
        this.deletedTaskList.add(deletedTask);
    }
    
    public Task getLatestDelete () {
        return this.deletedTaskList.remove(size() - 1);
    }
    
    public int size() {
        return this.deletedTaskList.size();
    }
    
    public void clear() {
        this.deletedTaskList.clear();
    }
}
