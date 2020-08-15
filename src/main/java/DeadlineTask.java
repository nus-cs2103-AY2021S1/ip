package main.java;

public class DeadlineTask extends Task{
    public static final String DEADLINE = "[D]";
    public DeadlineTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return DEADLINE + super.toString();
    }
}
