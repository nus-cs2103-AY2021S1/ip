package main.java;

public class Deadline extends Task{

    private String dueDate;

    public Deadline(String name, String dueDate){
        super(name);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
