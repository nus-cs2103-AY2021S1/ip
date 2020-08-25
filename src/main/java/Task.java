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

    /**
     * Converts a line of text in save file to a Task object.
     * @param in The string to be converted.
     * @return Task represented by the input string.
     */
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

    /**
     * Converts the date and time to the list display format.
     * @param date Date to be converted
     * @return String to be printed.
     */
    public static String dateToString (LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy h.mma"));
    }

    /**
     * Converts the date and time to the save file format.
     * @param date Date to be converted
     * @return String to be written to the save file.
     */
    public static String dateToSave (LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"));
    }
}
