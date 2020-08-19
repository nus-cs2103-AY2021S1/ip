public class Event extends Task {
    private String time;

    public Event(String title, String time) {
        super(title);
        this.time = time;
    }

    @Override
    public String toString() {
        String completeSymbol = this.complete ? "[/]" : "[X]";
        return String.format("[E]%s %s (at: %s)", completeSymbol, this.title, this.time);
    }
}