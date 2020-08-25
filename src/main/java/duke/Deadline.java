package main.java.duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    String taskType = "D";
    LocalDate localDate = null;
    LocalTime localTime = null;
    String[] descSplitBySlash = null;
    String[] descSplitBySpace = null;

    public Deadline(String description) {
        super(description);
    }

    public LocalTime stringToLocalTime(String hour, String minutes) {
        LocalTime lc = LocalTime.parse(hour +":" + minutes);
        return lc;
    }

    /**
     * Returns a formatted description of the Deadline task, with the date and time dateline wrapped in parenthesis
     * @return a formatted description of the Deadline task, with the date and time dateline wrapped in parenthesis
     */
    public String formattedDescription() {
        // an array where index 0 contains "deadline return book"
        // an array where index 1 contains "by 2019-10-15 1800"
        descSplitBySlash = super.description.split("/", 2);
        if (descSplitBySlash.length > 1) {
            // description came from user input
            // an array where index 0 contains "by"
            // index 1 contains date
            // index 2 contains time
            descSplitBySpace = descSplitBySlash[1].split(" ", 3);
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
            return descSplitBySlash[0];
        }

    }

    /**
     * Returns a string representation of the Deadline object.
     * @return a string representation of the Deadline object.
     */
    public String toString() {
        return "[" + taskType + "]" + "[" + super.getStatusIcon()
                + "] " + formattedDescription();
    }
}
