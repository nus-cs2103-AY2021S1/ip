package main.java;

public class DoneUnknownException extends DoneException {
    @Override
    public String toString(){
        String s="********************************************\n";
        s+="Please provide the number of the task to be marked\n";
        s+="********************************************\n";
        return s;
    }
}
