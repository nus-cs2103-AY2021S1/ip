package main.java;

public class EventException extends DukeException{
    @Override
    public String toString(){
        String s="********************************************\n";
        s+="OOPS!!! The description of a event cannot be empty.\n";
        s+="********************************************\n";
        return s;
    }
}
