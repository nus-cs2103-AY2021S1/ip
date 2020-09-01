package duke.Command;

import duke.Command.Command;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class ListC extends Command {


    @Override
    public void execute(Ui ui, TaskList todoList, Storage store){
        System.out.println("Here's your schedule lil dude");
        for (Task task :
                todoList.getTodoList()) {
            System.out.println(todoList.getID(task) + 1 + ". " + task.toString());
        }
        if (todoList.size() == 0) {
            System.out.println("Your life is empty now bruh");
        }
    }
}
