public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String txtFileFormat() {
        return "T ~/~ " + super.txtFileFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
