public class Event extends Task {
    public Event(String work){
        super(work.substring(0, work.indexOf("/") - 1) +
                " (at: " + work.substring(work.indexOf("/") + 4) + ")");
    }

    public Event(String work, String deadline){
        super(work + "(at:" + deadline);
    }
    public String toString(){
        return "[E]" + super.toString();
    }
}
