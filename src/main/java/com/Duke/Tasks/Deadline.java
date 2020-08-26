package com.Duke.Tasks;

import com.Duke.TaskManager.DukeException;

public class Deadline extends Task{
    private final String deadline;
    public Deadline(String task, String deadline, boolean isDone)throws DukeException {
        super(task,isDone);
        this.deadline = deadline;
    }

    public String getDeadline(){
        return "(by:" + this.deadline + ")";
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
