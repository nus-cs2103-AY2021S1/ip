package Parser;

import Command.DeleteCommand;
import Errors.ErrorExceptions;
import Tasks.TaskManager;
import Tasks.task;
import UI.UserInterface;
import java.util.NoSuchElementException;

public class ParseDelete extends Parse{
    public static void execute(int i) throws ErrorExceptions{
        DeleteCommand.execute(i);
    }
}
