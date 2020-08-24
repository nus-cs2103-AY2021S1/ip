package main.java;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public void clear(){
        taskList.clear();
    }

    public int size(){
        return taskList.size();
    }

    public boolean addAll(List<Task> list){
        return taskList.addAll(list);
    }

    public boolean add(Task task){
        return taskList.add(task);
    }

    public Task get(int index){
        return taskList.get(index);
    }

    public Task delete(int position){
        return taskList.remove(position);
    }
}
