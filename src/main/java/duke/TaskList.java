package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    
    public ArrayList<Task> getList() {
        return this.taskList;
    }
    
    public Task get(int number) throws DukeException {
        try {
            return taskList.get(number);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide valid index of the task");
        }
    }
    
    public void addTask(Task task) {
        taskList.add(task);
    }
    
    public void finishTask(int number) {
        Task task = taskList.get(number);
        task.completeTask();
        System.out.println("Nice! I've marked this task as done: \n" + task);
    }
    
    public Task deleteTask(int number) {
        Task task = taskList.get(number);
        taskList.remove(task);
        return task;
    }
    
    public int getSize() {
        return taskList.size();
    }
}
