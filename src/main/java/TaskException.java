package main.java;

public class TaskException extends DukeException {
    @Override
    public String toString(){
        String s="********************************************\n";
        s+="OOPS!!! The description of a todo cannot be empty.\n";
        s+="********************************************\n";
        return s;
    }
}
