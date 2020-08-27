package com.Duke.Commands;


public abstract class Command {

    public abstract void execute();

    public boolean isDone(){
        return false;
    }
}
