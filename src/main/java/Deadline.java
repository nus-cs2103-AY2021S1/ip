package main.java;

public class Deadline extends Task {

    protected String date;

    // Constructor for Deadline
    public Deadline(String description, String date) {
        super(description);
        this.date= date;
    }

    // Return string representation of Deadline
    @Override
    public String toString() {
        return " [D]" + super.toString() + " (by: " + date + ")";
    }
}
