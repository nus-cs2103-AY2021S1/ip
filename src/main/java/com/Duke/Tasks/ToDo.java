package com.Duke.Tasks;

import com.Duke.TaskManager.DukeException;

public final class ToDo extends Task{

    public ToDo(String task, boolean isDone) throws DukeException {
        super(task,isDone);
    }

    @Override
    public ToDo done() throws DukeException{
        return new ToDo(this.task, true);
    }
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
