package com.Duke.Commands;


public abstract class Command {

    public abstract String execute();

    public boolean isDone() {
        return false;
    }

}
