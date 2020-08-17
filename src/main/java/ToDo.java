public class ToDo extends Task {

    protected String by;

    public ToDo(String description) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}
