public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String savedFormat() {
        return "T " + super.savedFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
