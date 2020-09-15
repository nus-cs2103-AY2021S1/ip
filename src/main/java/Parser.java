import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    //constants used to parse user input
    public static String TODO = "todo";
    public static String DEADLINE = "deadline";
    public static String EVENT = "event";
    public static String DONE = "done";
    public static String DELETE = "delete";
    public static String FIND = "find";
    public static String LIST = "tasks";
    public static String CLEAR = "clear";
    public static String NEW = "new";
    public static String NOTES = "notes";
    public static String VIEW = "view";
    public static String COMPLETE = "complete";
    public static int TODO_LENGTH = "todo ".length();
    public static int DEADLINE_LENGTH = "deadline ".length();
    public static int EVENT_LENGTH = "event ".length();
    public static int DONE_LENGTH = "done ".length();
    public static int DELETE_LENGTH = "delete ".length();
    public static int FIND_LENGTH = "find ".length();
    public static int VIEW_LENGTH = "view ".length();
    public static int NEW_LENGTH = "new ".length();
    public static int AT_LENGTH = "/at ".length();
    public static int BY_LENGTH = "/by ".length();
    public static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yy HHmm");
    /**
     * Converts the string to a type of command.
     *
     * @param in The string to be parsed.
     * @return The command called by the input string.
     */
    public static Command parse(String in, boolean isWriting) {
        if (in.startsWith(COMPLETE)){
            return Command.COMPLETE;
        } else if (isWriting) {
            return Command.WRITE;
        } else if ("".equals(in)) {
            return Command.BLANK;
        } else if (in.equals(CLEAR)) {
            return Command.CLEAR;
        } else if (in.equals(LIST)) {
            return Command.LIST;
        } else if (in.startsWith(DONE)){
            return Command.DONE;
        } else if (in.startsWith(DELETE)){
            return Command.DELETE;
        } else if (in.startsWith(FIND)){
            return Command.FIND;
        } else if (in.startsWith(NEW)){
            return Command.NEW;
        } else if (in.startsWith(NOTES)){
            return Command.NOTES;
        } else if (in.startsWith(VIEW)){
            return Command.VIEW;
        } else if (in.startsWith(TODO) || in.startsWith(DEADLINE) || in.startsWith(EVENT)){
            return Command.ADD;
        } else {
            return Command.ERROR;
        }
    }

    public static String msg;

    /**
     * Handles reading add task commands and creating the task.
     *
     * @param in The string to be parsed.
     * @return The task required by the input string.
     */
    public static Task getTask(String in) {
        String taskName;
        Task temp = null;
        if (in.equals(TODO) | in.equals(EVENT) | in.equals(DEADLINE)) {
            msg = Ui.errorMsg("the task description cannot be nothing D:");
        } else if (in.startsWith("todo ")){
            taskName = in.substring(TODO_LENGTH);
            if (taskName.length() == 0) {
                msg = Ui.errorMsg("the task description cannot be nothing D:");
            } else {
                temp = new ToDo(taskName);
            }
        } else if (in.startsWith("deadline ")){
            int ind = in.indexOf("/by ");

            if (ind < 0 || in.endsWith("/by ")) {
                msg = Ui.errorMsg("you haven't entered a time that this task is due by. " +
                        "you can do that by typing \"deadline xxx /by dd/mm/yy hhmm\". \n" +
                        "e.g.: deadline read textbook /by 12/3/20 1500");
            } else if (ind - 1 <= DEADLINE_LENGTH) {
                msg = Ui.errorMsg("the task description cannot be nothing D:");
            } else {
                taskName = in.substring(DEADLINE_LENGTH,ind - 1);
                LocalDateTime dead = LocalDateTime.parse(in.substring(ind + BY_LENGTH),
                        dateFormat);
                temp = new Deadline(taskName,dead);
            }
        } else if (in.startsWith("event ")){
            int ind = in.indexOf("/at ");
            if (ind < 0 || in.endsWith("/at ")) {
                msg = Ui.errorMsg("you haven't entered a time that this task happens at. " +
                        "you can do that by typing \"event xxx /at dd/mm/yy hhmm\". \n" +
                        "e.g.: event read textbook /at 12/3/20 1500");
            } else if (ind - 1 <= EVENT_LENGTH) {
                msg = Ui.errorMsg("the task description cannot be nothing D:");
            } else {
                taskName = in.substring(EVENT_LENGTH, ind - 1);
                LocalDateTime time = LocalDateTime.parse(in.substring(ind + AT_LENGTH),
                        dateFormat);
                temp = new Event(taskName, time);
            }
        }
        return temp;
    }
}

