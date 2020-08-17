public class ToDo extends Task {

    public ToDo(String name) throws BlankTaskException {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
