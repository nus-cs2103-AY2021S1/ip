package com.Duke.Tasks;

import com.Duke.TaskManager.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This Class encapsulates the command Event
 */
public class Event extends Task{
    private final LocalTime start;
    private final LocalTime end;
    private final LocalDate date;

    /**
     * Constructor for Event class
     * @param task Represents the description of the task
     * @param start Represents the start time of the event
     * @param end Represents the end time of the event
     * @param isDone Represents whether the task is done
     * @param date
     */
    public Event(String task, LocalTime start, LocalTime end, boolean isDone, LocalDate date)throws DukeException {
        super(task,isDone);
        this.start = start;
        this.end = end;
        this.date = date;
    }

    /**
     * Method to Create the text format that is Saved
     */
    @Override
    public String toSaveFormat(){
        return isDone
                ? "E*" + task + "*" + start + "*"+ end + "*" + date + "*Y"
                : "E*" + task + "*" + start + "*"+ end + "*" + date + "*N";
    }

    public String getDeadline(){
        return "(on: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " at: "
                + start.format(DateTimeFormatter.ofPattern("HH:mm"))
                + " - "
                + end.format(DateTimeFormatter.ofPattern("HH:mm"))
                + ")";
    }


    /**
     * Method to create the done version of this event
     * @return Returns a Event with the isDone state as true
     */
    @Override
    public Event done() throws DukeException{
        return new Event(task, start, end, true, date);
    }

    @Override
    public String toString(){
        return this.isDone ?
                ("[E][\u2713] " + getTask() + getDeadline())
                :
                ("[E][\u2718] " + getTask() + getDeadline());
    }
}
