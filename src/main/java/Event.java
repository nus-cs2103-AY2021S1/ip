public class Event extends Task {
    private DateTime time;

    public Event(String title, String time) {
        super(title);
        this.time = new DateTime(time);
    }

    public Event(String title, Boolean isComplete, String time) {
        super(title, isComplete);
        this.time = new DateTime(time);
    }

    @Override
    public String saveString() {
        int completeSymbol = this.complete ? 1 : 0;
        return String.format("E|%d|%s|%s", completeSymbol, this.title, this.time.saveString());
    }

    @Override
    public String toString() {
        String completeSymbol = this.complete ? "[/]" : "[X]";
        return String.format("[E]%s %s (at: %s)", completeSymbol, this.title, this.time);
    }
}