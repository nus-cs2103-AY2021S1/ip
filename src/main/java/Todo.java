public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, Boolean done) {
        super(description, done);
    }

    @Override
    public String inputStyle() {
        return "todo " + super.inputStyle() ;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
