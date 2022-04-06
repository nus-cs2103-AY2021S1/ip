package com.Duke.Tasks;

import com.Duke.TaskManager.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DoWithin extends Task{
    private final LocalDate dateStart;
    private final LocalDate dateEnd;

    /**
     * Constructor for the DoWithin class
     * @param task Represents the description of the task
     * @param dateStart Represents the start date of the task
     * @param dateEnd Represents the end date of the task
     * @param isDone Represents whether the task is done
     */
    public DoWithin(String task, LocalDate dateStart, LocalDate dateEnd, boolean isDone)throws DukeException {
        super(task,isDone);
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public String getWithinPeriod(){
        return "(within: " + dateStart.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " - " + dateEnd.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")" ;
    }

    /**
     * Method to create the done version of this task
     * @return Returns a DoWithin with the isDone state as true
     */
    @Override
    public DoWithin done() throws DukeException{
        assert dateEnd.isAfter(dateEnd);
        return new DoWithin(task, dateStart, dateEnd, true);
    }

    /**
     * Method to Create the text format that is Saved
     */
    @Override
    public String toSaveFormat(){
        return isDone
                ? "W*" + task + "*" + dateStart + "*" + dateEnd + "*Y"
                : "W*" + task + "*" + dateStart + "*" + dateEnd + "*N";
    }

    @Override
    public String toString(){
        return this.isDone ?
                ("[W][\u2713] " + getTask() + getWithinPeriod())
                :
                ("[W][\u2718] " + getTask() + getWithinPeriod());
    }
}
