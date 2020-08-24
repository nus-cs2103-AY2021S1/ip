package Utility;

import Utility.Choice;
import Utility.DukeException;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    public Choice parseCommand(String input) throws DukeException {
        ArrayList<String> commands = new ArrayList<>(
                Arrays.asList("DONE", "LIST", "DEADLINE", "EVENT", "TODO", "BYE", "DELETE"));
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
