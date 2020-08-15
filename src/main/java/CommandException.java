package main.java;

public class CommandException extends DukeException {
    @Override
    public String toString(){
        String s="********************************************\n";
        s+="OOPS!!! I don't know what that commands mean.\n";
        s+="********************************************\n";
        return s;
    }
}
