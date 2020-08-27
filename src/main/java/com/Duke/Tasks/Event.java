package com.Duke.Tasks;

import com.Duke.TaskManager.DukeException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/*
 * This Class encapsulates the command Event
 */
public class Event extends Task{
    private final LocalTime start;
    private final LocalTime end;

    /*
     * Constructor for Event class
     * @param task Represents the description of the task
     * @param isDone Represents whether the task is done
     * @param start Represents the start time of the event
     * @param end Represents the end time of the event
     */
    public Event(String task, LocalTime start, LocalTime end, boolean isDone)throws DukeException {
        super(task,isDone);
        this.start = start;
        this.end = end;
    }

    /*
     * Method to Create the text format that is Saved
     */
    @Override
    public String toSaveFormat(){
        return isDone
                ? "E*" + task + "*" + start + "*"+ end + "*Y"
                : "E*" + task + "*" + start + "*"+ end + "*N";
    }

    public String getDeadline(){
        return "(at: " + this.start.format(DateTimeFormatter.ofPattern("HH:mm")) + " - " + this.end.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
    }

    /*
     * Method to create the done version of this event
     * @return Returns a Event with the isDone state as true
     */
    @Override
    public Event done() throws DukeException{
        return new Event(this.task, this.start, this.end, true);
    }

    @Override
    public String toString(){
        return this.isDone ?
                ("[E][\u2713] " + this.getTask() + this.getDeadline())
                :
                ("[E][\u2718] " + this.getTask() + this.getDeadline());
    }
}
