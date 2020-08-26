package com.DukeBot;

public class Task {
    //This class manages the information of each task within Dukes Task List
    protected final String task;
    protected final boolean isDone;

    Task(String task, boolean isDone) throws DukeException{
        if(task.trim().isEmpty()) {
            throw new DukeException("The description of a task cannot be empty.");
        }else{
            this.task = task;
            this.isDone = isDone;
        }

    }

    public boolean getIsDone(){
        return this.isDone;
    }

    public Task done() throws DukeException{
        return new Task(this.task, true);
    }

    public String getTask(){
        return this.task;
    }

    @Override
    public String toString(){
        return this.isDone ?
                ("[\u2713] " + this.getTask())
                :
                ("[\u2718] " + this.getTask());
    }
}
