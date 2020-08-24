package main.java;

public class Deadline extends Task {
    private String date;

    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toSaveFormat() {
        return "D" + super.toSaveFormat() + " | " + date;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.date);
    }
}
