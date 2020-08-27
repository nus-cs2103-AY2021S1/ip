import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Parser {
    public static int getTaskId(String command) {
       return Integer.parseInt(command.split(" ")[1]) - 1;
    }

    public static Task getTask(String taskString) throws DukeException {
        char taskType = taskString.charAt(1);
        boolean isTaskDone = taskString.charAt(4) == '\u2713';
        Task result;
        
        if (taskType == 'T') {
            String taskDesc = taskString.substring(7);
            result = new ToDo(taskDesc);
        } else {
            String[] strArr = taskString.split(taskType == 'D' ? " \\(by: " : " \\(at: ");
            String taskDesc = strArr[0].substring(7);
            String time = strArr[1].substring(0, strArr[1].length() - 1);
            result = taskType == 'D' ? new Deadline(taskDesc, time) : new Event(taskDesc, time);
        }

        if (isTaskDone) result.markAsDone();
        return result;
    }

    public static String getAction(String command) {
        String[] command_lst = command.split(" ", 2);
        return command_lst[0];
    }

    public static String getDetail(String command) {
        String[] command_lst = command.split(" ", 2);
        return command_lst[1];
    }
    
    public static String getDeadlineDesc(String command) {
        return getDetail(command).split(" /by ")[0];
    }
    
    public static String getBy(String command) {
        return getDetail(command).split(" /by ")[1];
    }

    public static String getEventDesc(String command) {
        return getDetail(command).split(" /at ")[0];
    }
    
    public static String getAt(String command) {
        return getDetail(command).split(" /at ")[1];
    }
    
    public static LocalDateTime getLocalDateTimeBy(String by) {
        String parsableBy = by.replace(" ", "T");
        return LocalDateTime.parse(parsableBy);
    }

    public static LocalDateTime getLocalDateTimeStart(String at) {
        String[] dateTimeArr = at.split(" to ");
        String parsableStart = dateTimeArr[0].replace(" ", "T");
        return LocalDateTime.parse(parsableStart);
    }

    public static LocalDateTime getLocalDateTimeEnd(String at) {
        String[] dateTimeArr = at.split(" to ");
        String parsableEnd = dateTimeArr[1].replace(" ", "T");
        return LocalDateTime.parse(parsableEnd);
    }
    
    public static String getStringStart(LocalDateTime start) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm");
        return start.format(formatter);
    }

    public static String getStringEnd(LocalDateTime end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm");
        return end.format(formatter);
    }

    public static String getDateTime(LocalDateTime by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm");
        return by.format(formatter);

    }

    public static String getDay(LocalDateTime by) {
        return by.getDayOfWeek().getDisplayName(TextStyle.SHORT, new Locale("en"));
    }

    public static String getStartDay(LocalDateTime start) {
        return start.getDayOfWeek().getDisplayName(TextStyle.SHORT, new Locale("en"));
    }

    public static String getEndDay(LocalDateTime end) {
        return end.getDayOfWeek().getDisplayName(TextStyle.SHORT, new Locale("en"));
    }
}
