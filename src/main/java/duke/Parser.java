package duke;

import commands.ExitCommand;
import commands.Command;
import commands.ListCommand;
import commands.HelpCommand;
import commands.DoneCommand;
import commands.DeleteCommand;
import commands.AddCommand;

public class Parser {

    private boolean quit = false;

    public boolean isQuit() {
        return quit;
    }

    public Command parse(String input) {
        if (input.equals("exit")) {
            quit = true;
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("help")) {
            return new HelpCommand();
        } else if (input.startsWith("done")) {
            return new DoneCommand(Integer.parseInt(input.substring(input.length() - 1)));
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(Integer.parseInt(input.substring(input.length() - 1)));
        } else {
            return new AddCommand(input);
        }
    }
}
