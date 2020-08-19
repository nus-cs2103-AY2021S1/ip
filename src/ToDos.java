public class ToDos extends Task {

    public ToDos(String description, int number) {
        super(description, number);
    }

    @Override
    public String toString() {
        return String.format("%s%s%s", "[T]", this.getIcon(), description);
    }
}
