package duke.task;

import duke.exception.IncorrectFormatException;
import duke.ui.UIPrint;
import duke.time.Time;

/**
 * A Deadline is a task with a deadline. Deadline objects store both task
 * description and deadline time.
 */
public class Deadline extends Task {

    private Time deadline;

    private Deadline(String icon, String description, String deadline, String taskInfo) {
        super(icon, description, taskInfo);
        this.deadline = Time.stringToTime(deadline);
    }

    @Override
    public String getTaskType() {
        return "deadline";
    }

    /**
     * Creates a Deadline using a string with task info,
     * throws exceptions when the string has wrong format.
     * @param deadlineInfo the string of task info
     * @return the Deadline object created
     */
    public static Deadline createDeadline(String deadlineInfo) {
        String[] splitStr = deadlineInfo.split(" /by ", 2);

        checkException(splitStr);

        String description = splitStr[0];
        String deadline = splitStr[1];

        Deadline newDeadline = new Deadline(UIPrint.deadlineIcon, description, deadline, deadlineInfo);

        return newDeadline;
    }

    private static void checkException(String[] splitStr) {
        if (splitStr.length != 2) {
            String line = UIPrint.getLine(UIPrint.star, 50);
            String errMessage =
                    line + "\nPlease follow the format of deadline <duke.task description> /by <deadline>\n" + line;
            throw new IncorrectFormatException(errMessage);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline + ")";
    }
}
