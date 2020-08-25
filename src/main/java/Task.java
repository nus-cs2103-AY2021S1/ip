import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    public boolean done;
    public String task;
    public Task (String in) {
        task = in;
        done = false;
    }
    public void complete() {
        done = true;
    }
    public String saveText() {
        return done + " | " + task;
    }
    public static Task readText(String in) {
        String[] result = in.split(" \\| ");
        String taskType = result[0];
        boolean temp = Boolean.parseBoolean(result[1]);
        Task tempTask;
        if (taskType.equals("T")) {
            tempTask = new ToDo(result[2]);
        } else if (taskType.equals("D")) {
            tempTask = new Deadline(result[2],LocalDateTime.parse(result[3],DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")));
        } else {
            tempTask = new Event(result[2],LocalDateTime.parse(result[3],DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")));
        }
        if (temp) {
            tempTask.complete();
        }
        return tempTask;
    }
    public static String dateToString (LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy h.mma"));
    }
    public static String dateToSave (LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"));
    }
}
