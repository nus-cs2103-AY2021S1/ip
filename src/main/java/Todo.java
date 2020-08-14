public class Todo extends Task {
    Todo(String content) {
        super(content);
    }

    @Override
    public String returnStringForm() {
        return "[T]" + super.returnStringForm();
    }
}
