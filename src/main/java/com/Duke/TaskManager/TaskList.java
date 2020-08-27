package com.Duke.TaskManager;

import com.Duke.Tasks.Task;

import java.util.List;

public class TaskList {
    //This class manages the list of Tasks that the user has added
    public final List<Task> ls;

    public TaskList(List<Task> ls) {
        this.ls = ls;
    }

    public int length(){
        return ls.size();
    }

    public void setDone(int task) throws DukeException {
        if(task>ls.size()){
            throw new DukeException("You don't have that many tasks");
        }else {
            ls.set(task, ls.get(task).done());
        }
    }

    public List<Task> getListOfTasks(){
        return this.ls;
    }

    public void delete(int task) throws DukeException{
        if(task>ls.size()){
            throw new DukeException("You don't have that many tasks");
        }else{
            System.out.println("     Noted. I've removed this task: ");
            System.out.println("       " + ls.get(task-1).toString());
            ls.remove(task-1);
        }
    }

    public void add(Task task){
        ls.add(task);
    }


}
