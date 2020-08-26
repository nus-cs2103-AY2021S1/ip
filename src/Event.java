public class Event extends Task {

    private String timePeriod;

    public Event(String name, String timePeriod){
        super(name);
        this.timePeriod = timePeriod;
    }

    @Override
    public String getType(){
        return "E";
    }

    @Override
    public String getTime(){
        return timePeriod;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timePeriod + ")";
    }

}
