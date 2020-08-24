public class Event extends Task{

    public Event(String description, String date) {
        super(description, date);
    }

    public String writeToFile() {
        return "E|" + super.writeToFile();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at :" + this.date + ")";
    }
}
