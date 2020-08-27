package Command;

import Errors.ErrorExceptions;
import Tasks.TaskManager;
import Tasks.task;
import UI.UserInterface;

/**
 * Represents a command that marks a task as completed.
 */
public class CompletedCommand extends Command{
    /**
     * Marks the selected task as completed.
     *
     * @param i task index.
     * @throws ErrorExceptions failed to locate specified task.
     */
    public static void execute(int i) throws ErrorExceptions {
        task t;
        int index = i;
        try {
            t = TaskManager.getTask(index);
            TaskManager.completed(t);
            UserInterface.done();
            System.out.println("    " + TaskManager.read(t));
            System.out.println("The tracked Tasks.task has been marked as completed! Congrats~~!");
        } catch(IndexOutOfBoundsException e){
            throw new ErrorExceptions("There is no such Tasks.task!");
        }
    }
}
