public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String createSaveDataLine() {
        return "T|" + getStatusLetter() + "|" + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
