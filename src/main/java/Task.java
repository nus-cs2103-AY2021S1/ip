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
        String check = isDone ? "v" : "x";
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
        Task task = null;
        switch(c) {
        case TODO_SYMBOL:
            task = new ToDo(desc);
            break;
        case DEADLINE_SYMBOL:
            task = parseToTask(desc, TaskType.DEADLINE);
            break;
        case EVENT_SYMBOL:
            task = parseToTask(desc, TaskType.EVENT);
            break;
        default:
            System.out.println("Error: could not recognize task symbol");
        }
        if (task == null) {
            return null;
        }

        String check = line.substring(0, 1);
        if (check.equals("v")) {
            task.done();
        }
        return task;

    }

    public static Task parseToTask(String line, TaskType type) {
        if (!line.contains("/")) {
            System.out.println("Error: invalid task format for deadline/event");
            return null;
        }
        String[] arr = line.split("/", 2);
        try {
            LocalDateTime ldt = LocalDateTime.parse(arr[1].trim(), formatter);
            return type == TaskType.DEADLINE ? new Deadline(arr[0], ldt)
                                            : new Event(arr[0], ldt);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
