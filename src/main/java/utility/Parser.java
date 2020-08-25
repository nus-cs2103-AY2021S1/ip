package utility;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {

    /**
     * Reads user input and takes the first word to convert it
     * into ENUM format.
     * @param input User input in string.
     * @return Choice ENUM
     * @throws DukeException
     */
    public Choice parseCommand(String input) throws DukeException {
        ArrayList<String> commands = new ArrayList<>(
                Arrays.asList("DONE", "LIST", "DEADLINE", "EVENT", "TODO", "BYE", "DELETE", "FIND"));
        String arr[] = input.split(" ");
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
