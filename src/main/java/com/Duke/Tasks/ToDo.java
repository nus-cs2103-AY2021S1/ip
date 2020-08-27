package com.Duke.Tasks;

import com.Duke.TaskManager.DukeException;

/*
 * This Class encapsulates the command To Do
 */
public final class ToDo extends Task{

    /*
    * Constructor for the To Do class
    * @param task Represents the description of the task
    * @param isDone Represents whether the task is done
    */
    public ToDo(String task, boolean isDone) throws DukeException {
        super(task,isDone);
    }

    /*
     * Method to create the done version of this task
     * @return Returns a To Do with the isDone state as true
     */
    @Override
    public ToDo done() throws DukeException{
        return new ToDo(this.task, true);
    }

    /*
     * Method to Create the text format that is Saved
     */
    @Override
    public String toSaveFormat(){
        return isDone
                ? "T*" + task + "*Y"
                : "T*" + task + "*N";
    }

    @Override
    public String toString(){
        return this.isDone ?
                ("[T][\u2713] " + this.getTask())
                :
                ("[T][\u2718] " + this.getTask());
    }

}
