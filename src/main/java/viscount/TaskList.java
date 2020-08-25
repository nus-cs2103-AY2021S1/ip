package viscount;

import viscount.exception.ViscountIndexOutOfBoundsException;
import viscount.task.Task;

import java.util.List;

public class TaskList {
    private List<Task> tasks;
    
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void markDone(int taskIndex) throws ViscountIndexOutOfBoundsException {
        try {
            Task task = tasks.get(taskIndex);
            task.setDone(true);
        } catch (IndexOutOfBoundsException e) {
            throw new ViscountIndexOutOfBoundsException(taskIndex);
        }
    }

    public Task remove(int taskIndex) throws ViscountIndexOutOfBoundsException {
        try {
            return tasks.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new ViscountIndexOutOfBoundsException(taskIndex);
        }
    }
    
    public List<Task> getTasks() {
        return tasks;
    }
    
    public Task getTask(int taskIndex) throws ViscountIndexOutOfBoundsException {
        try {
            return tasks.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new ViscountIndexOutOfBoundsException(taskIndex);
        }
    }
    
    public int getTasksSize() {
        return tasks.size();
    }
}
