import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    //constants used to parse user input
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String DONE = "done";
    public static final String DELETE = "delete";
    public static final String FIND = "find";
    public static final String LIST = "tasks";
    public static final String CLEAR = "clear";
    public static final String NEW = "new";
    public static final String NOTES = "notes";
    public static final String VIEW = "view";
    public static final String COMPLETE = "complete";
    public static final int TODO_LENGTH = "todo ".length();
    public static final int DEADLINE_LENGTH = "deadline ".length();
    public static final int EVENT_LENGTH = "event ".length();
    public static final int DONE_LENGTH = "done ".length();
    public static final int DELETE_LENGTH = "delete ".length();
    public static final int FIND_LENGTH = "find ".length();
    public static final int VIEW_LENGTH = "view ".length();
    public static final int NEW_LENGTH = "new ".length();
    public static final int AT_LENGTH = "/at ".length();
    public static final int BY_LENGTH = "/by ".length();
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yy HHmm");
    private static String msg;

    /**
     * Get the message returned when encountering a parsing error.
     *
     * @return The message returned.
     */
    public static String getMsg() {
        return msg;
    }

    /**
     * Converts the string to a type of command.
     *
     * @param in The string to be parsed.
     * @return The command called by the input string.
     */
    public static Command parse(String in, boolean isWriting) {
        if (in.startsWith(COMPLETE)) {
            return Command.COMPLETE;
        } else if (isWriting) {
            return Command.WRITE;
        } else if (in.toLowerCase().equals("lol")) {
            return Command.LOL;
        } else if (in.toLowerCase().startsWith("justin gets attacked")) {
            return Command.JUSTIN;
        } else if ("".equals(in)) {
            return Command.BLANK;
        } else if (in.equals(CLEAR)) {
            return Command.CLEAR;
        } else if (in.equals(LIST)) {
            return Command.LIST;
        } else if (in.startsWith(DONE)) {
            return Command.DONE;
        } else if (in.startsWith(DELETE)) {
            return Command.DELETE;
        } else if (in.startsWith(FIND)) {
            return Command.FIND;
        } else if (in.startsWith(NEW)) {
            return Command.NEW;
        } else if (in.startsWith(NOTES)) {
            return Command.NOTES;
        } else if (in.startsWith(VIEW)) {
            return Command.VIEW;
        } else if (in.startsWith(TODO) || in.startsWith(DEADLINE) || in.startsWith(EVENT)) {
            return Command.ADD;
        } else {
            return Command.ERROR;
        }
    }

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
            msg = Responses.TASK_NO_DESCRIPTION;
        } else if (in.startsWith("todo ")) {
            taskName = in.substring(TODO_LENGTH);
            if (taskName.length() == 0) {
                msg = Responses.TASK_NO_DESCRIPTION;
            } else {
                temp = new ToDo(taskName);
            }
        } else if (in.startsWith("deadline ")) {
            int ind = in.indexOf("/by ");
            if (ind < 0 || in.endsWith("/by ")) {
                msg = Responses.DEADLINE_INPUT_EG;
            } else if (ind - 1 <= DEADLINE_LENGTH) {
                msg = Responses.TASK_NO_DESCRIPTION;
            } else {
                taskName = in.substring(DEADLINE_LENGTH, ind - 1);
                LocalDateTime deadline = LocalDateTime.parse(in.substring(ind + BY_LENGTH), DATE_FORMAT);
                temp = new Deadline(taskName, deadline);
            }
        } else if (in.startsWith("event ")) {
            int ind = in.indexOf("/at ");
            if (ind < 0 || in.endsWith("/at ")) {
                msg = Responses.EVENT_INPUT_EG;
            } else if (ind - 1 <= EVENT_LENGTH) {
                msg = Responses.TASK_NO_DESCRIPTION;
            } else {
                taskName = in.substring(EVENT_LENGTH, ind - 1);
                LocalDateTime time = LocalDateTime.parse(in.substring(ind + AT_LENGTH), DATE_FORMAT);
                temp = new Event(taskName, time);
            }
        }
        return temp;
    }
}

