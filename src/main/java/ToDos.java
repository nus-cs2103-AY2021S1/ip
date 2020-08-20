public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
        this.type = "ToDos";
    }

    public String toString() {
        return "  [" + this.getType() + "][" + this.getStatusIcon() + "] "
                + this.getDescription();
    }
}
