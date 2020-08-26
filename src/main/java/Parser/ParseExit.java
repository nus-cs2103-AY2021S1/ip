package Parser;

import Command.ExitCommand;
import Errors.ErrorExceptions;
import UI.UserInterface;

public class ParseExit extends Parse{
    public static void execute() throws ErrorExceptions {
        ExitCommand.execute();
    }
}
