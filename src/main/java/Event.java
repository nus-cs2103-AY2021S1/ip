public class Event extends Task {
    private String date;


    public Event(String description, String date) {
        super(description, "E");
        this.date = date;
    }

    @Override
    public String generateSaveFormat() {
        return super.generateSaveFormat() + Duke.line + date;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", date);
    }
}
