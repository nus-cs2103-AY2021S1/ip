import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected boolean isDone;
    protected String task;

    public Task (String in) {
        task = in;
        isDone = false;
    }

    public void complete() {
        isDone = true;
    }

    public String saveText() {
        return isDone + " | " + task;
    }

    //constants used to parse save text
    public static String separator = " \\| ";
    public static DateTimeFormatter dateFormat= DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
    /**
     * Converts a line of text in save file to a Task object.
     *
     * @param in The string to be converted.
     * @return Task represented by the input string.
     */
    public static Task readText(String in) {
        String[] result = in.split(separator);
        String taskType = result[0];
        boolean isComplete = Boolean.parseBoolean(result[1]);
        String taskName = result[2];
        String taskTime;
        Task tempTask;
        if (taskType.equals("T")) {
            tempTask = new ToDo(taskName);
        } else {
            taskTime = result[3];
            if (taskType.equals("D")) {
                tempTask = new Deadline(taskName,LocalDateTime.parse(taskTime,dateFormat));
            } else if (taskType.equals("E")) {
                tempTask = new Event(taskName,LocalDateTime.parse(taskTime,dateFormat));
            } else {
                System.out.println("error reading save file");
                return null;
            }
        }
        if (isComplete) {
            tempTask.complete();
        }
        return tempTask;
    }

    /**
     * Converts the date and time to the list display format.
     *
     * @param date Date to be converted
     * @return String to be printed.
     */
    public static String dateToString (LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy h.mma"));
    }

    /**
     * Converts the date and time to the save file format.
     *
     * @param date Date to be converted
     * @return String to be written to the save file.
     */
    public static String dateToSave (LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"));
    }
}
