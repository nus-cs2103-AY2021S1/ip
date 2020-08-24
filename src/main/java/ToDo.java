package main.java;

public class ToDo extends Task {
    ToDo(String name){
        super(name);
    }

    @Override
    public String toSaveFormat() {
        return "T" + super.toSaveFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
