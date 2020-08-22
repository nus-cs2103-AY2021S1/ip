public class Todo extends Task {
    Todo(String item, boolean completed) {
        super(item, completed);
    }

    @Override
    public String getItem() {
        return "[T]" + super.getItem();
    }
}
