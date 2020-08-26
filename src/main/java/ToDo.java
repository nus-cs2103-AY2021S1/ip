public class ToDo extends Task {

    ToDo(String task) {
        super(task);
    }

    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
