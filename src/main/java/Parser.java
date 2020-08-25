import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public static LocalDate dateParser(String input) throws DukeException {
        try {
            input = input.replace("/", "-");
            LocalDate ret = LocalDate.parse(input);
            return ret;
        } catch (DateTimeParseException ex1) {
            throw new DukeException("Wrong formatting!");
        }
    }

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

    private static String getFirstWord(String text) {

        int index = text.indexOf(' ');

        if (index > -1) { // Check if there is more than one word.

            return text.substring(0, index).trim(); // Extract first word.

        } else {

            return text; // Text is the first word itself.
        }
    }

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
