public class ToDos extends Task {

    public ToDos( String taskDesc) {
        super(taskDesc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
