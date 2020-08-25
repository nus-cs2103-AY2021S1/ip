package duke.parts;

import duke.command.*;
import duke.error.UnknownAction;

public class Parser {

    public static Command parse(String input) throws UnknownAction {
        if (input.indexOf("todo") == 0 || input.indexOf("deadline") == 0 || input.indexOf("event") == 0) {
            return new AddCommand(input);
        } else if (input.equals("list")) {
            return new PrintCommand();
        } else if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.indexOf("delete") == 0) {
            String index = input.replaceAll("\\D+","");
            return new DeleteCommand(Integer.parseInt(index));
        } else if (input.indexOf("find") == 0) {
            int space = input.indexOf(" ") + 1;
            String word = input.substring(space, input.length());
            return new FindCommand(word);
        } else {
            throw new UnknownAction();
        }
    }
}
