package main.java;

public class DoneOutOfBoundException extends DoneException {
    @Override
    public String toString(){
        String s="********************************************\n";
        s+="OOPS!!! There are no such task.\n";
        s+="********************************************\n";
        return s;
    }
}
