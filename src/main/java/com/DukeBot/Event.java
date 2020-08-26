package com.DukeBot;

public class Event extends Task{
    private final String start;
    private final String end;

    Event(String task, String start, String end, boolean isDone)throws DukeException{
        super(task,isDone);
        this.start = start;
        this.end = end;
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
