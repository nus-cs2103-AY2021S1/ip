public class ToDo extends Task {
    /**
     * Constructor for ToDo.
     *
     * @param description ToDo task description
     **/
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getStoredString(){
        return "T" + super.toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
