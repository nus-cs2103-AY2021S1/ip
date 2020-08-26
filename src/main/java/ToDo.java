public class ToDo extends Task {
    protected String at;

    public ToDo(String description) {
        super(description,"T");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
