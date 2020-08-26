public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String saveAs() { return "T | " + super.saveAs(); }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}