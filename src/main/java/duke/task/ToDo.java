package duke.task;

public class ToDo extends Task {
    public ToDo(String description, boolean done) {
        super(description, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    
    @Override
    public String saveToHardDisk() {
        return "T" + super.saveToHardDisk();
    }
}
