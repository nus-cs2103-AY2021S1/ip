package duke.parts;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.PrintCommand;
import duke.error.UnknownAction;

public class Parser {

    public static Command parse(String input) throws UnknownAction {
        if(input.indexOf("todo") == 0 || input.indexOf("deadline") == 0 || input.indexOf("event") == 0) {
            return new AddCommand(input);
        } else if(input.equals("list")) {
            return new PrintCommand();
        } else if(input.equals("bye")) {
            return new ExitCommand();
        } else if(input.indexOf("delete") == 0) {
            String index = input.replaceAll("\\D+","");
            return new DeleteCommand(Integer.parseInt(index));
        } else {
            throw new UnknownAction();
        }
    }
}
