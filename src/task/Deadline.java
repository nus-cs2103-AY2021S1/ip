package task;

import exception.IncorrectFormatException;
import ui.UIPrint;
import time.Time;

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
                    line + "\nPlease follow the format of deadline <task description> /by <deadline>\n" + line;
            throw new IncorrectFormatException(errMessage);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline + ")";
    }
}
