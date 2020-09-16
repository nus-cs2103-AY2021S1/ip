package duke.task;

import duke.exception.EmptyTimeException;
import duke.exception.ExceptionMessage;
import duke.exception.IncorrectFormatException;
import duke.time.Time;
import duke.ui.UiPrint;

/**
 * A Event is a task with a time. Event objects store both task description
 * and time.
 */
public class Event extends Task {

    private Event(String icon, String description, String time, String taskInfo) {
        super(icon, description, taskInfo);
        setTime(Time.stringToTime(time));
    }

    @Override
    public String getTaskType() {
        return "event";
    }

    /**
     * Creates a Event using a string with task info,
     * throws exceptions when the string has wrong format.
     * @param eventInfo the string of task info
     * @return the Event object created
     */
    public static Event createEvent(String eventInfo) {
        String[] splitStr = eventInfo.split(" /at ", 2);

        checkException(splitStr);

        String description = splitStr[0];
        String time = splitStr[1];

        Event newEvent = new Event(UiPrint.EVENT_ICON, description, time, eventInfo);

        return newEvent;
    }

    private static void checkException(String[] splitStr) throws IncorrectFormatException {
        if (splitStr.length != 2) {
            String errMessage = ExceptionMessage.EVENT_INCORRECT_FORMAT_MESSAGE;
            throw new IncorrectFormatException(errMessage);
        }

        if (splitStr[1].isBlank()) {
            String errMessage = ExceptionMessage.EMPTY_TIME_MESSAGE;
            throw new EmptyTimeException(errMessage);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + getTime() + ")";
    }
}
