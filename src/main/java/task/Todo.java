package task;

public class Todo extends Task {
    public Todo(String item, boolean isCompleted) {
        super(item, isCompleted);
    }

    @Override
    public String getItem() {
        return "[T]" + super.getItem();
    }

    @Override
    public String getInput() {
        return "[T]" + super.getItem();
    }
}
