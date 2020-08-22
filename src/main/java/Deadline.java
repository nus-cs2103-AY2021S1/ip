package main.java;

public class Deadline extends Task {

    public Deadline(String description, String by) {
        super(description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.time + ")";
    }
    
    @Override
    public String toStringFile() {
        return "D" + " | " + (isDone? "1" : "0") + " | " + this.description + " | " + super.time;
    }
}
