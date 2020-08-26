package com.DukeBot;

public class DukeException extends Exception{
    private final String errorMsg;

    DukeException(String errorMsg){
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString(){
        return "     \u2639 OOPS!!!" + this.errorMsg;
    }
}
