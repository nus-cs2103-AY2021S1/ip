package main.java;

public class ToDo extends Task {
    ToDo(String name){
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
