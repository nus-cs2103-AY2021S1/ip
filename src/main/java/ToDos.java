public class ToDos extends Task {

    protected String by;

    public ToDos(String description, String by) {
        super(description);
        this.by = by;
        tag = "T";
    }

    public ToDos(String description) {
        super(description);
        this.by = null;
        tag = "T";
    }

    @Override
    public String getTaskType() {
        return tag;
    }

    public String toPrint(){
        return by == null
                ? super.toPrint()
                : super.toPrint() + "|" + by;
    }

    @Override
    public String toString() {
        return by == null ? "[T]" + super.toString() : "[T]" + super.toString() + " (by: " + by + ")" ;
    }
}