package main.java.duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    String taskType = "E";
    LocalDate localDate = null;
    LocalTime localTime = null;
    String[] descSplitBySlash = null;
    String[] descSplitBySpace = null;

    public Event(String description) {
        super(description);
    }

    public LocalTime stringToLocalTime(String hour, String minutes) {
        LocalTime lc = LocalTime.parse(hour +":" + minutes);
        return lc;
    }

    public String formattedDescription() {
        assert super.description.length() > 0 : "task description is empty";
        // an array where index 0 contains "deadline return book"
        // an array where index 1 contains "by 2019-10-15 1800"
        descSplitBySlash = super.description.split("/", 2);
        if (descSplitBySlash.length > 1) {
            // description came from user input
            // an array where index 0 contains "by"
            // index 1 contains date
            // index 2 contains time
            descSplitBySpace = descSplitBySlash[1].split(" ", 3);
            assert descSplitBySpace.length == 3 : "deadline is not in the format /by 2011-11-29 1830";
            localDate = LocalDate.parse(descSplitBySpace[1]);
            String timeString = descSplitBySpace[2];
            localTime = stringToLocalTime(timeString.substring(0, timeString.length() - 2),
                    timeString.substring(timeString.length() - 2));
            return descSplitBySlash[0] + "(" + descSplitBySpace[0] + ": "
                    + localDate.getMonth() + " "
                    + localDate.getDayOfMonth() + " "
                    + localDate.getYear()
                    +", "
                    + localTime.format(DateTimeFormatter.ofPattern("HH:mm a"))
                    + ")";
        } else {
            // description came from System file.
            assert descSplitBySlash.length == 1 : "There is a '/' inside the description of this task.";
            return descSplitBySlash[0];
        }

    }

    public String toString() {
        return "[" + taskType + "]" + "[" + super.getStatusIcon()
                + "] " + formattedDescription();
    }
}
