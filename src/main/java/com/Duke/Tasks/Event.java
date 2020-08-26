package com.Duke.Tasks;

import com.Duke.TaskManager.DukeException;

public class Event extends Task{
    private final String start;
    private final String end;

    public Event(String task, String start, String end, boolean isDone)throws DukeException {
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
        return "(at:" + this.start + "-" + this.end + ")";
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
