public class ToDo extends Task { //added

    public ToDo(String description) {
        super(description, Type.TODO);
    }

    public ToDo(String description, boolean isDone) {
        super(description, Type.TODO, isDone);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
