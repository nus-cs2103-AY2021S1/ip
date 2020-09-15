package duke;

import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {

    /**
     * Parses the String input into the YYYY/MM/DD format.
     * @param input String input from user
     * @return LocalDate in the correct format
     * @throws DukeException Throws exception if the input is invalid or in an unrecognisable format.
     */
    public static LocalDate dateParser(String input) throws DukeException {
        try {
            input = input.replace("/", "-");
            LocalDate ret = LocalDate.parse(input);
            return ret;
        } catch (DateTimeParseException ex1) {
            throw new DukeException("Wrong formatting! Please provide in YYYY/MM/DD format.");
        }
    }

    /**
     * Converts the 24Hour into AM/PM format.
     * @param input String that represents time in 24Hour.
     * @return String that represents time in AM/PM.
     */
    public static String timeParser(String input) {
        String ret = "";
        int hour = Integer.valueOf(input)/100;
        if (hour >= 12) {
            ret = "PM";
            ret = Integer.toString(hour == 12 ? 12 : hour - 12).concat(ret);
        } else {
            ret = "AM";
            ret = Integer.toString(hour == 0 ? 12 : hour).concat(ret);
        }
        return ret;
    }

    /**
     * Returns the first word a String
     * @param text any String
     * @return the first word in the string.
     */
    public static String getFirstWord(String text) {

        int index = text.indexOf(' ');

        if (index > -1) { // Check if there is more than one word.

            return text.substring(0, index).trim(); // Extract first word.

        } else {

            return text; // Text is the first word itself.
        }
    }

    public static Duke.Command determineSortType(String input) {
        String[] filter = input.split("/", 2);
        Duke.Command ret = Duke.Command.ERROR;
        if (filter[1].equals("type")) {
            ret = Duke.Command.SORTTYPE;
        }
        if (filter[1].equals("date")) {
            ret = Duke.Command.SORTDATE;
        }
        return ret;
    }

    /**
     * Returns a enum class item stored in duke.Duke based on the first word of the input.
     * @param input Command from user
     * @return duke.Duke Command to indicate which switch to enter
     */
    public static Duke.Command parse(String input) {
        assert !input.isEmpty();
        if (getFirstWord(input).equals("todo") || getFirstWord(input).equals("deadline") ||
                getFirstWord(input).equals("event")) {
            return Duke.Command.ADD;
        } else if (getFirstWord(input).equals("done")) {
            return Duke.Command.DONE;
        } else if (getFirstWord(input).equals("delete")) {
            return Duke.Command.DELETE;
        } else if (input.equals("list")) {
            return Duke.Command.LIST;
        } else if (input.equals("save")) {
            return Duke.Command.SAVE;
        } else if (getFirstWord(input).equals("find")) {
            return Duke.Command.FIND;
        } else if (getFirstWord(input).equals("sort")) {
            return determineSortType(input);
        } else if (input.equals("clear")) {
            return Duke.Command.CLEAR;
        } else if (input.equals("bye")) {
            return Duke.Command.BYE;
        } else {
            return Duke.Command.ERROR;
        }
    }
}
