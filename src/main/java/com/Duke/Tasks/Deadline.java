package com.Duke.Tasks;

import com.Duke.ProcessManager.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private final LocalDate deadline;
    public Deadline(String task, LocalDate deadline, boolean isDone)throws DukeException {
        super(task,isDone);
        this.deadline = deadline;
    }

    public String getDeadline(){
        return "(by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")" ;
    }

    @Override
    public Deadline done() throws DukeException{
        return new Deadline(this.task, this.deadline,true);
    }
    @Override
    public String toSaveFormat(){
        return isDone
                ? "D*" + task + "*" + deadline + "*Y"
                : "D*" + task + "*" + deadline + "*N";
    }

    @Override
    public String toString(){
        return this.isDone ?
                ("[D][\u2713] " + this.getTask() + this.getDeadline())
                :
                ("[D][\u2718] " + this.getTask() + this.getDeadline());
    }
}
