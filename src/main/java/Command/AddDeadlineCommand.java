package Command;

import Errors.ErrorExceptions;
import Tasks.TaskManager;

import static Parser.InputManager.*;

public class AddDeadlineCommand extends Command {
    public static void execute(String input) throws ErrorExceptions {
        String name = getName(input, 2);
        String date = getDate(input,1);
        TaskManager.newTask(name,"Deadline",date, getFileDir());
    }
}
