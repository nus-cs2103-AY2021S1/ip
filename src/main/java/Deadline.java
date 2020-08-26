import java.time.format.DateTimeFormatter;

public class Deadline extends TimedTask {

    Deadline(String name, String dateTime) {
        super(name, dateTime);
        taskType = "D";
    }

    Deadline(String name, Boolean isDone, String dateTime) {
        super(name, isDone, dateTime);
        taskType = "D";
    }
    
    @Override
    public String toString() {
        return String.format("[%s]%s", taskType, super.toString());
    }
}