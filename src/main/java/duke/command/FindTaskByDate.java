package duke.command;

import duke.io.Layout;
import duke.io.Parser;
import duke.task.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class FindTaskByDate extends Command {
    
    public FindTaskByDate(ArrayList<Task> tasks) {
        super(tasks);
    }

    public void findTaskByDate(String [] arr) {
        String date;
        try {
            date = parser.getDate(arr);
            ArrayList<Task> shallowCopy = new ArrayList<>(tasks);
            shallowCopy.removeIf(task ->
                    !(task.isSameDate(date))
            );
            layout.printTaskList(true, shallowCopy);
        } catch (DukeException e) {
            layout.print(e.getMessage());
        }

    }
    
}
