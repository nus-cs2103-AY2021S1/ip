public class ToDo extends Task {

    public ToDo(String name) throws BlankTaskException {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String[] attributeList() {
        return new String[] { "T", getName(), String.valueOf(isDone()) };
    }
}
