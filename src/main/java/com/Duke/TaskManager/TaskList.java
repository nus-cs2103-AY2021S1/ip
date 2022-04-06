package com.Duke.TaskManager;

import com.Duke.Tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * This class models the list of pending or done tasks managed by Duke
 */
public class TaskList {
    public final List<Task> ls;

    public TaskList(List<Task> ls) {
        this.ls = ls;
    }

    public int length(){
        return ls.size();
    }

    /**
     * This method takes a int which represents the position of the task and
     * sets the task to be done
     * @param task
     * @throws DukeException
     */
    public void setDone(int task) throws DukeException {
        assert task > 0 : "Invalid task entered";
        if(task>ls.size()){
            throw new DukeException("You don't have that many tasks");
        }else {
            ls.set(task, ls.get(task).done());
        }
    }

    /**
     * Takes a hint string to find all tasks within the tasklist with similar task
     * names
     * @param hint
     * @return a list of tasks with similar task names to the hint
     */
    public TaskList findTask(String hint){
        assert hint !=null : "I couldn't understand the hint please try again";
        TaskList containsHint = new TaskList(new ArrayList<>());
        getListOfTasks()
                .stream()
                .filter(task->task
                        .getTask()
                        .contains(hint))
                .forEach(containsHint::add);
        return containsHint;
    }

    public List<Task> getListOfTasks() {
        return this.ls;
    }

    /**
     * Takes a int position of the task within this list and removes it
     * from the task list
     * @param task
     * @return returns the delete message to be displayed on GUI
     * @throws DukeException
     */
    public String delete(int task) throws DukeException {
        if(task>ls.size()) {
            throw new DukeException("You don't have that many tasks");
        }else{
            System.out.println("     Noted. I've removed this task: ");
            System.out.println("       " + ls.get(task-1).toString());
            String deleted = ls.get(task-1).toString();
            ls.remove(task-1);
            return "Noted. I've removed this task: \n"+deleted;
        }
    }

    public void add(Task task) {
        ls.add(task);
    }


}
