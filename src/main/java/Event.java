public class Event extends Task {
    private String dateAndTime;

    public Event(String description) {
        super(description.split("/on ")[0]);
        this.dateAndTime = description.split("/on ")[1];
    }

    public String dateAndTimeBracket() {
        
        return String.format("(on: %s)", this.dateAndTime);
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s %s",
                this.getStatusIcon(), this.description, this.dateAndTimeBracket());
    }
}
