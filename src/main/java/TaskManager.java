package main.java;

import java.util.List;
import java.util.ArrayList;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager(){
        this.tasks = new ArrayList<Task>();
    }

    public Task addToDo(String name){
        Task newTask = new ToDo(name);
        tasks.add(newTask);
        return newTask;
    }

    public Task addDeadLine(String name, String time){
        Task newTask = new Deadline(name,time);
        tasks.add(newTask);
        return newTask;
    }

    public Task addEvent(String name, String time){
        Task newTask = new Event(name, time);
        tasks.add(newTask);
        return newTask;
    }

    public int getTotalTask(){
        return this.tasks.size();
    }

    public void doTask(int index) throws TaskDoneException {
        this.tasks.get(index-1).setDone();
    }

    public String getTaskStatus(int index) {
        return tasks.get(index-1).toString();
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 1; i <= tasks.size(); i++) {
            output += i + "." + tasks.get(i-1).toString();
            if (i != tasks.size()) {
                output += "\n";
            }
        }
        return output;
    }
}
