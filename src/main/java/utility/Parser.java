package utility;

import java.util.Arrays;
import java.util.List;

/**
 * A Parser class that is in charge of parsing user input.
 */
public class Parser {
    /**
     * Function for returning Enums in a String array.
     * Solution below adapted from https://stackoverflow.com/a/16252290
     * @param e Enums
     * @return String array of enums.
     */
    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
    /**
     * Reads user input and takes the first word to convert it
     * into ENUM format.
     *
     * @param input User input in string.
     * @return Choice ENUM.
     * @throws DukeException Duke Custom Exception.
     */
    public Choice parseCommand(String input) throws DukeException {

        List<String> commands = Arrays.asList(getNames(Choice.class));
        System.out.println(commands);
        String[] arr = input.split(" ");
        String command = arr[0].toUpperCase();
        if (!commands.contains(command)) {
            String errMsg = "\n____________________________________________________________\n"
                    + "I'm sorry, I don't understand what you're asking.\n"
                    + "____________________________________________________________";
            throw new DukeException(errMsg);
        }
        return Choice.valueOf(command);
    }
}
