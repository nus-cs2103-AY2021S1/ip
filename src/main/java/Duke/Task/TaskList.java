package Duke.Task;

import java.util.ArrayList;

public class TaskList {
    
    protected ArrayList<Task> taskList;
    
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    
    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }
    
    public ArrayList<Task> getList() {
        return this.taskList;
    }
    
    //count number of tasks
    public String countNum() {
        int num = this.taskList.size();
        return "    Now you have " + num + " tasks in the list.";
    }
    
    public int getSize() {
        return taskList.size();
    }
    
    public Task get(int num) {
        return taskList.get(num);
    }
    
    public void remove(int num) {
        taskList.remove(num);
    }
    
    //add new to-do to the list
    public void addToDo(Todo newToDo) {
        this.taskList.add(newToDo);
    }

    //add new deadline to the list
    public void addDeadline(Deadline newDdl) {
        this.taskList.add(newDdl);
    }

    //add new event to the list
    public void addEvent(Event newEvent) {
        this.taskList.add(newEvent);
    }
}
