public class EventTask extends Task{

    private String time;

    public EventTask(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + time + ")";
    }
}
