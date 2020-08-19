public class Todo extends Task {

    public Todo(String input) {
        super(input);
    }

    @Override
    public String toString() {
        String completeSymbol = this.complete ? "[/]" : "[X]";
        return String.format("[T]%s %s", completeSymbol, this.title);
    }
}