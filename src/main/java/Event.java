package main.java;
public class Event extends Task {
    private String time;
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }
    public Event(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" +"[" + this.getStatusIcon()+"] " + this.description + "(at:" + this.time +")";
    }

    @Override
    public String writeToFile() {
        String result = "D # ";
        if(isDone) {
            result+="1 # ";
        } else {
            result+="0 # ";
        }
        result+=description;
        result+=" # "+time;
        return result;
    }
}
