package Command;

import Errors.ErrorExceptions;
import Tasks.TaskManager;

import static Parser.InputManager.getFileDir;
import static Parser.InputManager.getName;

public class AddTodoCommand extends Command {
    public static void execute(String input) throws ErrorExceptions {
        String name = getName(input, 1);
        TaskManager.newTask(name,"Todo",null, getFileDir());
    }
}
