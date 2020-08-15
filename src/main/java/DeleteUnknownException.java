package main.java;

public class DeleteUnknownException extends DukeException {
    @Override
    public String toString(){
        String s="********************************************\n";
        s+="Please provide the number of the task to be deleted\n";
        s+="********************************************\n";
        return s;
    }
}
