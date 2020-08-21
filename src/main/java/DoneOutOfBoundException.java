package main.java;

public class DoneOutOfBoundException extends DoneException {
    @Override
    public String toString(){
        String s = "OOPS!!! There are no such task.\n";
        return s;
    }
}
