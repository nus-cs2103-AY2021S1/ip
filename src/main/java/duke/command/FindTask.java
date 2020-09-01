package duke.command;

import duke.task.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class FindTask extends Command {
    
    public FindTask(ArrayList<Task> tasks) {
        super(tasks);
    }

    public String findTask(String [] arr) {
        String filterWord;
        try {
            filterWord = parser.getFilterWord(arr);
            ArrayList<Task> tasksCopy = new ArrayList<>(tasks);
            tasksCopy.removeIf(task ->
                    !(task.containsWord(filterWord))
            );
            return layout.printTaskList(true, tasksCopy);
        } catch (DukeException e) {
            return layout.print(e.getMessage());
        }
    }
    
}
