package tasks;

public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
    }

    @Override
    protected char getTaskType() {
        return 'T';
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
