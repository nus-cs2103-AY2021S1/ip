package Command;

import Errors.ErrorExceptions;
import Tasks.TaskManager;
import Tasks.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FilterDateCommand extends Command {
    public static void execute(LocalDate date) throws ErrorExceptions {
        System.out.println("Filtered called");
        ArrayList<task> clone = TaskManager.getStore();
        int count = 1;
        System.out.println("Here are your tasks on this date!");
        for(task i : clone){
            if(i.taskDate()!=null){
                if(i.taskDate().toLocalDate().equals(date)){
                    System.out.println("    " + count + ". " + TaskManager.read(i));
                    count++;
                }
            }
        }

    }
}
