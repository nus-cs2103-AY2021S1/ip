public class Event extends TimedTask {
    
    Event(String name, String dateTime) {
        super(name, dateTime);
        taskType = "E";
    }

    Event(String name, Boolean isDone, String dateTime) {
        super(name, isDone, dateTime);
        taskType = "E";
    }
    
    @Override
    public String toString() {
        return String.format("[%s]%s", taskType, super.toString());
    }
}