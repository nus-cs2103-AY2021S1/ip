public class Event extends Task {

    private String duration;
    public Event(String name, String duration) {
        super(name);
        this.duration = duration;
    }

    @Override
    public String printTask() {
        return "[E]" + super.printTask() + " (at: " + duration + ")";
    }

    @Override
    public String toSave() {
        return "E " + super.toSave() + " | " + this.duration;
    }
}
