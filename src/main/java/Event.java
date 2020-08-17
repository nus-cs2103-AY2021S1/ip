class Event extends Task {
    static String SYMBOL = "[E]";

    private String details;

    public Event(String name, String details) {
        super(name, TaskType.Event);
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        String tick = this.isDone() ? "[✓]" : "[✗]";
        return String.format("%s%s %s", SYMBOL, tick, name);
    }
}
