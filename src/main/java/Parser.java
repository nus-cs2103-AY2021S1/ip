import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static Command parse(String in) {
        if ("".equals(in)) {
            return Command.blank;
        } else if ("clear".equals(in)) {
            return Command.clear;
        } else if ("list".equals(in)) {
            return Command.list;
        } else if (in.startsWith("done ")){
            return Command.done;
        } else if (in.startsWith("delete ")){
            return Command.delete;
        } else if (in.startsWith("todo ") || in.startsWith("deadline ") || in.startsWith("event ")){
            return Command.add;
        } else {
            return Command.error;
        }
    }

    public static Task getTask(String in) {
        String taskName;
        Task temp = null;
        if (in.startsWith("todo ")){
            taskName = in.substring(5);
            if (taskName.length() == 0) {
                Ui.errorMsg("the task description cannot be nothing D:");
            } else {
                temp = new ToDo(taskName);
            }
        } else if (in.startsWith("deadline ")){
            int ind = in.indexOf("/by ");
            if (ind < 0 || ind == in.length() - 4) {
                Ui.errorMsg("you haven't entered a time that this task is due by. you can do that by typing \"deadline xxx /by dd/mm/yy hhmm\". \n" +
                        "e.g.: deadline read textbook /by 12/3/20 1500");
            } else if (ind - 1 <= 9) {
                Ui.errorMsg("the task description cannot be nothing D:");
            } else {
                taskName = in.substring(9,ind - 1);
                LocalDateTime dead = LocalDateTime.parse(in.substring(ind + 4), DateTimeFormatter.ofPattern("d/M/yy HHmm"));
                temp = new Deadline(taskName,dead);
            }
        } else if (in.startsWith("event ")){
            int ind = in.indexOf("/at ");
            if (ind < 0 || ind == in.length() - 4) {
                Ui.errorMsg("you haven't entered a time that this task happens at. you can do that by typing \"event xxx /at dd/mm/yy hhmm\". \n" +
                        "e.g.: event read textbook /at 12/3/20 1500");
            } else if (ind - 1 <= 6) {
                Ui.errorMsg("the task description cannot be nothing D:");
            } else {
                taskName = in.substring(6,ind - 1);
                LocalDateTime time = LocalDateTime.parse(in.substring(ind + 4), DateTimeFormatter.ofPattern("d/M/yy HHmm"));
                temp = new Event(taskName,time);
            }
        }
        return temp;
    }
}

