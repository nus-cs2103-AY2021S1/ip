import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
            throw new DukeException("Wrong formatting!");
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
    private static String getFirstWord(String text) {

        int index = text.indexOf(' ');

        if (index > -1) { // Check if there is more than one word.

            return text.substring(0, index).trim(); // Extract first word.

        } else {

            return text; // Text is the first word itself.
        }
    }

    /**
     * Returns a enum class item stored in Duke based on the first word of the input.
     * @param input Command from user
     * @return Duke Command to indicate which switch to enter
     */
    public static Duke.Command parse(String input) {
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
        } else if (input.contains("find")) {
            return Duke.Command.FIND;
        } else {
            return Duke.Command.ERROR;
        }
    }
}
