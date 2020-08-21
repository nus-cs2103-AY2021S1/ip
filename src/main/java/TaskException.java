package main.java;

public class TaskException extends DukeException {
    @Override
    public String toString(){
        String s = "OOPS!!! The description of a todo cannot be empty.";
        return s;
    }
}
