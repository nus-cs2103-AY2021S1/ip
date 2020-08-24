public class Event extends Task {
    protected String date;

    public Event(String name, boolean isCompleted, String date) {
        super(name, isCompleted);
        this.date = date;
    }

    public static Event newEvent(String name, String date){
        return new Event(name, false, date);
    }

    public static Event existingEvent(String name, boolean isCompleted, String date){
        return new Event(name, isCompleted, date);
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }

    public String toSaveString(){
        return "E" + " | " + super.toSaveString() + " | " + this.date + "\n";
    }
}
