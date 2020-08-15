package main.java;

public class DeadlineException extends DukeException {
    @Override
    public String toString(){
        String s="********************************************\n";
        s+="OOPS!!! The description of a deadline cannot be empty.\n";
        s+="********************************************\n";
        return s;
    }
}
