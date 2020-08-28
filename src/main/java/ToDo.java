public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }
    
    @Override
    public String encode() {
        return String.format("T | %d | %s", getStatusIcon(), description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
