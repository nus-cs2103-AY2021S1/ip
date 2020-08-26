package task;

import exception.IncorrectFormatException;
import ui.UIPrint;
import time.Time;

public class Event extends Task {

    private Time time;

    private Event(String icon, String description, String time, String taskInfo) {
        super(icon, description, taskInfo);
        this.time = Time.stringToTime(time);
    }

    @Override
    public String getTaskType() {
        return "event";
    }

    public static Event createEvent(String eventInfo) {
        String[] splitStr = eventInfo.split(" /at ", 2);

        checkException(splitStr);

        String description = splitStr[0];
        String time = splitStr[1];

        Event newEvent = new Event(UIPrint.eventIcon, description, time, eventInfo);

        return newEvent;
    }

    private static void checkException(String[] splitStr) throws IncorrectFormatException {
        if (splitStr.length != 2) {
            String line = UIPrint.getLine(UIPrint.star, 50);
            String errMessage =
                    line + "\nPlease follow the format of event <task description> /at <event time>\n" + line;
            throw new IncorrectFormatException(errMessage);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + time + ")";
    }
}
