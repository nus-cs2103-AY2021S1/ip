package duke;

import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * duke.Parser is a utility class that helps to decode duke.Duke text commands.
 */
public class Parser {
    /**
     * Split time data from the task
     */
    public static String[] splitTime(String line){
        return line.split(" \\/by | \\/at ");
    }

    /**
     * Parse time data from the task
     */
    public static LocalDate parseTime(String timeString){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(timeString, format);
    }

    /**
     * Utility method to parse text commands from the user.
     * @param command   Command that the User has entered into duke.Duke.
     * @return  A String array with the command being the first string and the task
     *          as the second string is there is a task.
     */
    public static String[] parseCommand(String command){
        return command.trim().split(" ",2);
    }
}
