package Command;

import Errors.ErrorExceptions;
import Tasks.TaskManager;

import static Parser.InputManager.*;

public class AddEventCommand extends Command {
    public static void execute(String input) throws ErrorExceptions {
        String name = getName(input, 2);
        String date = getDate(input,2);
        TaskManager.newTask(name,"Event",date, getFileDir());
    }
}
