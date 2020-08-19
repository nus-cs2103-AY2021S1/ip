public class Todo extends Task {

    public Todo(String input) {
        super(input);
    }

    @Override
    public String toString() {
        return this.complete
                ? String.format("[T][\u2713] %s", this.title)
                : String.format("[T][\u2717] %s", this.title);
    }
}