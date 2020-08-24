public class EventTask extends Task{

    private String time;

    public EventTask(String name, boolean isCompleted, String time) {
        super(name, isCompleted);
        this.time = time;
    }

    public String getType(){
        return "E";
    }

    public String getTime(){
        return this.time;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + time + ")";
    }
}
