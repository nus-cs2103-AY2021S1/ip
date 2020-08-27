package com.Duke.Tasks;

import com.Duke.ProcessManager.DukeException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private final LocalTime start;
    private final LocalTime end;

    public Event(String task, LocalTime start, LocalTime end, boolean isDone)throws DukeException {
        super(task,isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toSaveFormat(){
        return isDone
                ? "E*" + task + "*" + start + "*"+ end + "*Y"
                : "E*" + task + "*" + start + "*"+ end + "*N";
    }

    public String getDeadline(){
        return "(at: " + this.start.format(DateTimeFormatter.ofPattern("HH:mm")) + " - " + this.end.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
    }

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
