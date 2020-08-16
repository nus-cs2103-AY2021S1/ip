class Event extends Task {
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
        return String.format("%s (at: %s)", name, details);
    }
}
