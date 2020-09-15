package duke.command;

import duke.io.Layout;
import duke.io.Parser;
import duke.task.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represent find task by date command.
 */
public class FindTaskByDate extends Command {
    String [] input;
    public FindTaskByDate(String [] input) {
        this.input = input;
    }

    @Override
    public String execute(ArrayList<Task> tasks, Layout layout) {
        String date;
        try {
            date = Parser.getDate(input);
            ArrayList<Task> shallowCopy = new ArrayList<>(tasks);
            shallowCopy.removeIf(task ->
                    !(task.isSameDate(date))
            );
            return layout.printTaskList(true, shallowCopy);
        } catch (DukeException e) {
            return layout.print(e.getMessage());
        }
    }
    
}
