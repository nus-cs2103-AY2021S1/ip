import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {

    protected static final String TODO_SYMBOL = "[T]";
    protected static final String DEADLINE_SYMBOL = "[D]";
    protected static final String EVENT_SYMBOL = "[E]";

    protected String sym = "";
    protected LocalDateTime time;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    private String desc;
    private boolean isDone;

    public Task(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        String check = isDone ? "\u2713" : "\u2718";
        String timeText = time == null
                        ? ""
                        : "/" + time.format(formatter);
        return check + " " + sym + " " + desc + " " + timeText;
    }

    public void done() {
        isDone = true;
    }

    public void postpone(LocalDateTime newTime) {
        this.time = newTime;
    }

    public static Task parseToTask(String line) {
        String c = line.substring(2, 5);
        String desc = line.substring(6);
        String[] arr;

        switch(c) {
        case TODO_SYMBOL:
            return new ToDo(desc);
        case DEADLINE_SYMBOL:
            arr = desc.split("/", 2);
            try {
                LocalDateTime ldt = LocalDateTime.parse(arr[1].trim(), formatter);
                return new Deadline(arr[0], ldt);
            } catch (Exception e) {
                System.out.println(e);
                break;
            }
        case EVENT_SYMBOL:
            arr = desc.split("/", 2);
            try {
                LocalDateTime ldt1 = LocalDateTime.parse(arr[1].trim(), formatter);
                return new Event(arr[0], ldt1);
            } catch (Exception e) {
                System.out.println(e);
                break;
            }
        default:
            System.out.println("Error: could not recognize task symbol");
        }
        return null;
    }

}
