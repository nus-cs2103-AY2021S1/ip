package function;

import command.Command;
import data.DukeCommandSet;

import exception.IncorrectFormatException;
import exception.InvalidIndexException;
import exception.NoDescriptionException;
import exception.UnknownCommandException;

public class DukeFunction {

    public static void checkCommand(String str) {
        String[] inputParts = str.split(" ", 2);
        String possibleCommand = inputParts[0];
        String rest = inputParts.length == 2 ? inputParts[1] : "";
        Command command = null;

        try {
            command = DukeCommandSet.getInstance().getCommand(possibleCommand);
        } catch (UnknownCommandException exception) {
            System.out.println(exception.getMessage());
        }

        if (command != null) {
            try {
                command.execute(rest);
            } catch (NoDescriptionException | IncorrectFormatException | InvalidIndexException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
