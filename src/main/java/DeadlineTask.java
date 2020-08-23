package main.java;

public class DeadlineTask extends Task{
    public static final String DEADLINE = "[D]";

    public DeadlineTask() {}

    public DeadlineTask(String description) {
        super(description);
    }

    public String getType() {
        return DEADLINE;
    }


    @Override
    public String toString() {
        return DEADLINE + super.toString();
    }
}
