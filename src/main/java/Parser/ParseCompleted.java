package Parser;

import Command.CompletedCommand;
import Errors.ErrorExceptions;

public class ParseCompleted extends Parse{
    public static void execute(int i) throws ErrorExceptions{
        CompletedCommand.execute(i);
    }
}
