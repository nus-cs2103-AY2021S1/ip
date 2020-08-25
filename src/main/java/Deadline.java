package main.java;

public class Deadline extends Task {

    protected String date;

    // Constructor for Deadline
    public Deadline(String description, String date) {
        super(description);
        this.date= date;
    }

    // Get info to store in hard disk
    @Override
    public String[] getInfo() {
        return new String[] {"D", description, date};
    }

    // Return string representation of Deadline
    @Override
    public String toString() {
        return " [D]" + super.toString() + " (by: " + date + ")";
    }
}
