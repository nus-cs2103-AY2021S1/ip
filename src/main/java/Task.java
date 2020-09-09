import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {

    private static final String TODO_SYMBOL = "[T]";
    private static final String DEADLINE_SYMBOL = "[D]";
    private static final String EVENT_SYMBOL = "[E]";


    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE dd MMM HH:mm");
    protected String time = "";


    private String desc;
    private boolean isDone;

    public Task(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        String check = isDone ? "\u2713" : "\u2718";
        return check + " " + desc + " " + time;
    }

    public void done() {
        isDone = true;
    }

    public static Task parseToTask(String line) {
        String c = line.substring(2, 5);
        String desc = line.substring(6);

        switch(c) {
        case TODO_SYMBOL:
            return new ToDo(desc);
        case DEADLINE_SYMBOL:
            return new Deadline();
        case EVENT_SYMBOL:
            return new Event();
        default:
            System.out.println("Error: could not recognize task symbol");
            return null;
        }
    }

}
