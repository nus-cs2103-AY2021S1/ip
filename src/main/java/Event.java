public class Event extends Task {
    private String time;

    public Event(String title, String time) {
        super(title);
        this.time = time;
    }

    public Event(String title, Boolean isComplete, String time) {
        super(title, isComplete);
        this.time = time;
    }

    @Override
    public String saveString() {
        int completeSymbol = this.complete ? 1 : 0;
        return String.format("E|%d|%s|%s", completeSymbol, this.title, this.time);
    }

    @Override
    public String toString() {
        String completeSymbol = this.complete ? "[/]" : "[X]";
        return String.format("[E]%s %s (at: %s)", completeSymbol, this.title, this.time);
    }
}