public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public static ToDo create(String formattedString) {
        return new ToDo(formattedString.substring(5));
    }

    @Override
    public String toString() {
        char statusIcon = this.isDone ? 'X' : ' ';
        return String.format("[%c] ToDo: %s", statusIcon, this.description);
    }
}
