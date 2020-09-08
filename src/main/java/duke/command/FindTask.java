package duke.command;

import duke.io.Layout;
import duke.io.Parser;
import duke.task.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class FindTask extends Command {
    
    String [] input;
    
    public FindTask(String [] input) {
        this.input = input;
    }

    /**
     * Finds a task based on user input filter word.
     * @param tasks Existing task list.
     * @param layout Format the return output.
     * @return String to return to the user.
     */
    @Override
    public String execute(ArrayList<Task> tasks, Layout layout) {
        String filterWord;
        try {
            filterWord = Parser.getFilterWord(input);
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
