class Event extends Task {
    String time;

    Event(String name, String time) {
        super(name, Type.EVENT);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " (at: " + time + ")";
    }
}
