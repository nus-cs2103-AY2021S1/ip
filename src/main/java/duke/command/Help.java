package duke.command;

import duke.io.Layout;
import duke.task.Task;

import java.util.ArrayList;

public class Help extends Command {

    /**
     * Represent help command and return all valid commands.
     */
    @Override
    public String execute(ArrayList<Task> tasks, Layout layout) {
        String[] arr = new String[]{
                "Here are all your commands:",
                "list- list all tasks",
                "todo <description> - add task",
                "deadline <description> /by <due date> -add task with deadline",
                "event <description> /at <event date> -add event with date ",
                "date <one filter date> - finds task on specified date",
                "\t date can be formatted as : yyyy-mm-dd or dd/mm/yyyy",
                "\t time can be formatted as : HHmm or HH.mm a",
                "done <task number> - marks task as done",
                "find <one filter word> - finds task with specified word",
                "bye - goodbye!"
        };
        return layout.printCommands(arr);
    }
    
}
