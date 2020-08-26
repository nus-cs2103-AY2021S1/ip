package com.DukeBot;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    //This class manages the list of Tasks that the user has added
    private List<Task> ls= new ArrayList<>();

    public int length(){
        return ls.size();
    }

    public void setDone(int task) throws DukeException{
        if(task>ls.size()){
            throw new DukeException("You don't have that many tasks");
        }else {
            ls.set(task, ls.get(task).done());
            System.out.println("     Nice! I've marked this task as done: ");
            System.out.println("       " + ls.get(task).toString());
        }
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

    public void printTask() throws DukeException{
        if(ls.isEmpty()){
            throw new DukeException("Sorry you have no tasks in your list.");
        }else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < ls.size(); i++) {
                int j = i + 1;
                System.out.println("     " + j + "." + ls.get(i).toString());
            }
        }
    }

}
