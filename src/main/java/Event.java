
public class Event extends Task {

    protected String at;

    private Event(String taskName, String taskTime) {
        super(taskName);
        this.at = taskTime;
        this.tag = "E";
    }

    public static Event create(String taskDescription) throws DukeException{
        if(!taskDescription.contains("/at")){
            throw new DukeException("Please include '/at' in front of the event time period");
        }
        String[] NameTimePair = taskDescription.split(" /at");
        String taskName = NameTimePair[0];
        String taskTime = NameTimePair[1];
        return new Event(taskName,taskTime);
    }

    public static Event create(String taskName, String taskTime){
        return new Event(taskName,taskTime);
    }

    @Override
    public String toString(){
        String symbol = isDone ? "\u2713" : "\u2718";
        return String.format("[%s][%s] %s (at: %s)",tag,symbol,taskName,at);
    }

    @Override
    public String safeFileFormat(){
        int done = isDone ? 1 : 0;
        return String.format("%s | %d | %s | %s \n",tag,done,taskName,at);
    }
}