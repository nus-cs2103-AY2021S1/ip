public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        this.type = "T";
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone, "T");
    }

}