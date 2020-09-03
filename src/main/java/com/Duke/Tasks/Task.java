package com.Duke.Tasks;

import com.Duke.TaskManager.DukeException;

/*
 * This Class encapsulates a unnamed task
 */
public class Task {
    protected final String task;
    protected final boolean isDone;

    /*
     * Constructor for the To Do class
     * @param task Represents the description of the task
     * @param isDone Represents whether the task is done
     */
    public Task(String task, boolean isDone) throws DukeException {
        if(task.trim().isEmpty()) {
            throw new DukeException("The description of a task cannot be empty.");
        }else{
            this.task = task;
            this.isDone = isDone;
        }

    }


    /*
     * Method to Create the text format that is Saved
     */
    public String toSaveFormat(){
        return isDone
                ? task + "*Y"
                : task + "*N";
    }

    public boolean getIsDone(){
        return this.isDone;
    }

    /*
     * Method to create the done version of this task
     * @return Returns a task with the isDone state as true
     */
    public Task done() throws DukeException{
        return new Task(this.task, true);
    }

    public String getTask(){
        return this.task;
    }

    @Override
    public String toString(){
        return this.isDone ?
                ("[\u2713] " + this.getTask())
                :
                ("[\u2718] " + this.getTask());
    }
}
