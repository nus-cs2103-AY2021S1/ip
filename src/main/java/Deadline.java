package main.java;

public class Deadline extends Task {

    private String deadline;

    public Deadline(String msg, String deadline){
        super(msg);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
