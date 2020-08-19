public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("%s%s%s", "[T]", this.getIcon(), description);
    }
}
