package main.java.duke.task;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String record() {
        if(this.isCompleted) {
            return "T | 1 | " + description;
        } else {
            return "T | 0 | " + description;
        }
    }
}
