public class Event extends Task {

    protected String duration;

    public Event(String new_task, String duration) {
        super(new_task);
        this.duration = duration;
    }

    public String fileFormat() {
        return "E" + " | " + super.fileFormat() + " | " + duration;
    }
    
    public String timeConverted() {
        timeParser inputTime = new timeParser(duration);
        String outputTime = inputTime.timeConverter();
        return "E" + " | " + super.fileFormat() + " | " + outputTime;
        
    }

    @Override
    public String toString() {
        return "[E]" +  super.toString() + " (at: " + duration + ")";
    }
}

