public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    public ToDos(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("%s%s%s", "[T]", this.getIcon(), description);
    }

    @Override
    public String toSaveString() {
        return String.format("T | %s | %s", super.doneString(), this.description);
    }

}
