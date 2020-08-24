class Event extends Task {
    private String start;
    private String end;

    Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String saveText() {
        String completeStatus = super.isCompleted() ? "1" : "0";
;        return "E," + completeStatus + "," + super.getName() + "," + start + "," + end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (start: %s, end: %s)", super.toString(), start, end);
    }
}
