package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This parser class takes in Strings as input and
 * convert into an output type based on the requirements
 *
 * @author Alex
 * @version %I%
 */
public class Parser {

    /**
     * Empty constructor for instantiating
     */
    public Parser() {
    }

    /**
     * This method parse strings to Tasks.
     *
     * @param str The string that is being parsed to Tasks, it must have a specific format
     * @return returns a tas object that is the result of the parsed string
     */
    Task parseTxtToTask(String str) {
        try {
            String type = str.substring(1, 2);
            String status = str.substring(4, 5);
            String descriptionAndDate = str.substring(6);
            String[] arr = descriptionAndDate.split("\\(");
            String description = arr[0].trim();
            String date = "";
            Task task;


            if (type.equals("E") || type.equals("D")) {
                date = arr[1].substring(4).replace(")", "");
            }

            if (type.equals("T")) {
                task = new ToDo(description);
            } else if (type.equals("E")) {
                task = new Event(description, date);
            } else { // "D"
                task = new Deadline(description, parseDateAndTime(date));
            }

            if (status.equals("\u2713")) {
                task.markAsDone();
            }
            return task;
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println(e.getMessage() + "Error in tasks file");
            throw e;
        }
    }

    /**
     * This method split the input string based on one empty space.
     *
     * @param input The string that is being parsed into a String array
     * @return returns a String array based on the input split by one space
     */
    String[] parseString(String input) {
        String[] tokens = input.split(" ");
        return tokens;
    }

    /**
     * This method provides a LocalDateTime object based on a given input string.
     *
     * @param dateTime The string that consists of date and time information.
     *                 It must be in a fixed format.
     * @return returns a LocalDateTime object with
     * information stored for easy retrieval later on
     */
    LocalDateTime parseDateAndTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime d1 = LocalDateTime.parse(dateTime, formatter);
        return d1;
    }
}
