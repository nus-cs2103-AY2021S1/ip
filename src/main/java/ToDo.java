public class ToDo extends Task{
    ToDo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
