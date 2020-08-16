package main.java;

public class Deadline extends Task {

    private String deadline;

    public Deadline(String msg, String deadline){
        super(msg);
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
