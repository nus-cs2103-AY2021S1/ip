public class Event extends Task {

    private String date;

    public Event(String description, String date) {
        super(description);
        super.symbol = 'D';
        this.date = date;
    }

    public Event(String description, String date, boolean isCompleted) {
        super(description, isCompleted);
        super.symbol = 'D';
        this.date = date;
    }

    @Override
    public Event markCompleted() {
        return new Event(description, date, true);
    }

    @Override
    public String getStorageString() {
        String baseString = super.getStorageString();
        return String.format("%s | %s", baseString, date);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", symbol, super.toString(), date);
    }
}
