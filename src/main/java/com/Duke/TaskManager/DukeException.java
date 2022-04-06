package com.Duke.TaskManager;

public class DukeException extends Exception{
    private final String errorMsg;

    public DukeException(String errorMsg){
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString(){
        return "     \u2639 OOPS!!!" + errorMsg;
    }
}
