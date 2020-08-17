public class ToDos extends Task {
    ToDos(String task) {
        super(task);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
