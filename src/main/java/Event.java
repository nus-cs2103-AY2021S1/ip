class Event extends Task {
    String time;

    Event(String name, String time) {
        super(name, Type.EVENT);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
