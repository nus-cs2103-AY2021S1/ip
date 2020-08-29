package Command;

import Errors.ErrorExceptions;
import Tasks.TaskManager;
import Tasks.task;

import java.util.ArrayList;

public class FindCommand extends Command{
    public static void execute(String name) throws ErrorExceptions {
        ArrayList<task> clone = TaskManager.getStore();
        int count = 1;
        System.out.println("Here are your tasks with this keywords!");
        for (task i : clone) {
//            System.out.println(i.getTaskName());
//            System.out.println(name);
//            System.out.println(i.getTaskName().equals(name));
            if(i.getTaskName().contains(name)){
                System.out.println("    " + count + ". " + TaskManager.read(i));
                count++;
            }
        }
    }
}
