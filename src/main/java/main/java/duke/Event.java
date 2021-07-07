package main.java.duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class that encapsulates tasks that are events. This is a child class of Task.
 */
public class Event extends Task{

    String taskType = "E";
    LocalDate localDate;
    LocalTime localTime;

    public Event(String description) {
        super(description);
    }

    public LocalTime stringToLocalTime(String hour, String minutes) {
        LocalTime lc = LocalTime.parse(hour +":" + minutes);
        return lc;
    }

    /**
     * Returns the deadline inputted by the user with brackets surrounding the date and time.
     * @param byDateTime string in the format "by 2019-11-31 1700"
     * @return bracketed deadline format.
     */
    public String bracketedDeadline(String byDateTime) {
        // description came from user input
        // an array where index 0 contains "by"
        // index 1 contains date
        // index 2 contains time
        // String.split is called with limit 4 to make the time string free of whitespace before and after.
        String[] descSplitBySpace = byDateTime.split(" ", 4);
        assert descSplitBySpace.length == 4 || descSplitBySpace.length == 3 : "deadline is not in the format /by " +
                "2011-11-29 1830";
        localDate = LocalDate.parse(descSplitBySpace[1]);
        String timeString = descSplitBySpace[2];
        localTime = stringToLocalTime(timeString.substring(0, timeString.length() - 2),
                timeString.substring(timeString.length() - 2));
        return "(" + descSplitBySpace[0] + ": "
                + localDate.getMonth() + " "
                + localDate.getDayOfMonth() + " "
                + localDate.getYear()
                +", "
                + localTime.format(DateTimeFormatter.ofPattern("HH:mm a"))
                + ")";
    }

    /**
     * Returns a formatted description of the Deadline task, with the date and time dateline wrapped in parenthesis
     * @return a formatted description of the Deadline task, with the date and time dateline wrapped in parenthesis
     */
    public String formattedDescription() {
        assert super.description.length() > 0 : "task description is empty";
        // an array where index 0 contains "deadline return book"
        // and index 1 contains "by 2019-10-15 1800"
        String[] descSplitBySlash = super.description.split("/", 2);
        if (descSplitBySlash.length > 1) {
            String dateTimeWBrackets = bracketedDeadline(descSplitBySlash[1]);
            return descSplitBySlash[0] + dateTimeWBrackets;
        } else {
            // description came from System file.
            assert descSplitBySlash.length == 1 : "There is a '/' inside the description of this task.";
            return descSplitBySlash[0];
        }

    }

    public String toString() {
        String priorityString = super.priority != null
                ? "[" + super.priority.toString() + "] "
                : " ";
        return "[" + taskType + "]" + "[" + super.getStatusIcon()
                + "]" + priorityString + formattedDescription();
    }
}
