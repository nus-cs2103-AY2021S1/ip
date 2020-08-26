package Command;

import Errors.ErrorExceptions;
import Tasks.TaskManager;
import Tasks.task;
import UI.UserInterface;

import java.util.NoSuchElementException;

public class DeleteCommand extends Command {
    public static void execute(int i) throws ErrorExceptions {
        task t;
        int index = i;
        try {
            t = TaskManager.getTask(index);
            TaskManager.delete(index);
            UserInterface.done();
            System.out.println("    " + TaskManager.read(t));
            System.out.println("The tracked Tasks.task has been deleted!");
        } catch(NoSuchElementException e){
            throw new ErrorExceptions("There is no suck Tasks.task!");
        }
    }
}
