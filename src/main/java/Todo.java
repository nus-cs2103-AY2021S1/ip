public class Todo extends Task {
    Todo(String item) {
        super(item);
    }

    @Override
    public String getItem() {
        return "[T]" + super.getItem();
    }
}
