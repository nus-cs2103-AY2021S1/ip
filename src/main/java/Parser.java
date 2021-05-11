import java.util.ArrayList;
import java.util.EnumSet;

/**
 * The {@code Parser} class processes and parses user input, coming up with a response.
 */
public class Parser {

    /**
     * Initialises Parser object.
     */
    public Parser() {
    }

    /**
     * Processes user input and returns an appropriate reply.
     *
     * @param lst The {@code TaskList} that keeps track of all pending tasks.
     * @param msg User input.
     * @return Response to user input.
     * @throws JimmyException If user input contains an error.
     */
    public static String parse(TaskList lst, String msg) throws JimmyException {

        assert msg != null : "msg should not be null";

        String[] arr = msg.split(" ");
        boolean isLong = arr.length > 1;
        String reply;

        if (!isLong) {

            switch (msg) {

            case "list":
                reply = "Here are your pending tasks:\n\n"
                    + lst.toString();
                break;

            case "bye":
                reply = "Bye. Hope to see you again soon!";
                break;

            case "help":
                reply = "Here are a list of commands available:\n\n"
                    + getHelpOptions();
                break;

            case "todo":
            case "event":
            case "deadline":
            case "find":
                throw new JimmyException(ErrorMessage.EMPTY_DESCRIPTION);

            case "done":
            case "delete":
                throw new JimmyException(ErrorMessage.EXCESS_DESCRIPTION);

            default:
                throw new JimmyException(ErrorMessage.UNKNOWN_FUNCTION);
            }
        } else {

            String firstWord = arr[0];

            switch (firstWord) {

            case "done":
            case "delete":
                ifExcessDescriptionThenThrow(arr);

                int index = Integer.parseInt(arr[1]);
                ifExceedRangeThenThrow(lst, index);
                String tentativeReply = ifDoneGetReply(lst, firstWord, index);
                reply = ifDeleteGetReply(lst, firstWord, index, tentativeReply);

                break;

            case "todo":
            case "event":
            case "deadline":

                Task taskToBeAdded = getAppropriateTask(firstWord, msg);
                lst.addTask(taskToBeAdded);
                reply = "Got it. I've added this task:\n  "
                    + taskToBeAdded + "\nNow you have "
                    + lst.getNumTasks()
                    + " tasks in the list.";

                break;

            case "find":
                String desc = msg.split("find ")[1];
                String tasks = lst.findTasksWith(desc);
                reply = "Here are the matching tasks in your list:\n" + tasks;
                break;

            default:
                throw new JimmyException(ErrorMessage.UNKNOWN_FUNCTION);
            }
        }
        return reply;
    }

    private static void ifExcessDescriptionThenThrow(String[] msgArray) throws JimmyException {
        if (msgArray.length > 2) {
            throw new JimmyException(ErrorMessage.EXCESS_DESCRIPTION);
        }
    }

    private static void ifExceedRangeThenThrow(TaskList taskList, int index) throws JimmyException {
        if (index > taskList.getNumTasks() || index < 1) {
            throw new JimmyException(ErrorMessage.TASK_EXCEED_RANGE);
        }
    }

    private static String ifDoneGetReply(TaskList taskList, String firstWord, int index) {
        if (firstWord.equals("done")) {
            taskList.completeTask(index);
            Task completedTask = taskList.getTask(index);
            return "Nice! I've marked this task as done:" + "\n\t  " + completedTask;
        } else {
            return null;
        }
    }

    private static String ifDeleteGetReply(TaskList taskList, String firstWord, int index, String reply) {
        if (firstWord.equals("delete")) {
            Task taskToBeDeleted = taskList.getTask(index);
            taskList.deleteTask(index);
            return "Noted. I've removed this task:" + "\n\t  " + taskToBeDeleted
                + "\nNow you have " + taskList.getNumTasks() + " tasks in the list.";
        } else {
            return reply;
        }
    }

    private static Task getAppropriateTask(String firstWord, String message) {
        if (firstWord.equals("todo")) {
            return new Todo(message);
        } else if (firstWord.equals("event")) {
            return new Event(message);
        } else {
            return new Deadline(message);
        }
    }

    private static String getHelpOptions() {
        ArrayList<HelpMessage> helpMessageList = new ArrayList<>(EnumSet.allOf(HelpMessage.class));
        StringBuilder helpMessages = new StringBuilder();
        for (HelpMessage message : helpMessageList) {
            helpMessages.append(message.getHelpMessage() + "\n");
        }
        return helpMessages.toString();
    }

}
