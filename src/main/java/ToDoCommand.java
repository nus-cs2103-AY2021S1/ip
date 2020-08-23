package main.java;

import java.util.ArrayList;

public class ToDoCommand extends Command {

    String[] commandDetails;

    public ToDoCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println("~ \n Got it. I've added this task: ");
        ToDo toDo = new ToDo(commandDetails[1]);
        tasks.add(toDo);
        System.out.println(String.format("   %s \n Now you have %d tasks in the list. \n~",
                toDo, tasks.size()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
