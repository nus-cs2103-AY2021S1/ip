package com.Duke.Tasks;

import com.Duke.TaskManager.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * This Class encapsulates the command Deadline
 */
public class Deadline extends Task{
    private final LocalDate deadline;

    /*
     * Constructor for the To Do class
     * @param task Represents the description of the task
     * @param deadline Represents the deadline date of the task
     * @param isDone Represents whether the task is done
     */
    public Deadline(String task, LocalDate deadline, boolean isDone)throws DukeException {
        super(task,isDone);
        this.deadline = deadline;
    }

    public String getDeadline(){
        return "(by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")" ;
    }

    /*
     * Method to create the done version of this task
     * @return Returns a Deadline with the isDone state as true
     */
    @Override
    public Deadline done() throws DukeException{
        return new Deadline(task, deadline,true);
    }

    /*
     * Method to Create the text format that is Saved
     */
    @Override
    public String toSaveFormat(){
        return isDone
                ? "D*" + task + "*" + deadline + "*Y"
                : "D*" + task + "*" + deadline + "*N";
    }

    @Override
    public String toString(){
        return this.isDone ?
                ("[D][\u2713] " + getTask() + getDeadline())
                :
                ("[D][\u2718] " + getTask() + getDeadline());
    }
}
